
# This is the server logic for a Shiny web application.
# You can find out more about building applications with Shiny here:
#
# http://shiny.rstudio.com
# http://www.generatedata.com/

library(shiny)
library(XML)
library(ggplot2)

shinyServer(function(input, output) {
  
  dataReactive <- reactive({
    book = xmlParse("dataNov-29-2014.xml")
    df = xmlToDataFrame("dataNov-29-2014.xml", stringsAsFactors=FALSE)
    class(df$Pin)
    df$Pin  = as.numeric(df$Pin)
    class(df$Money)
    df$Money = gsub("\\.", "", df$Money) # DELETE .
    df$Money = as.numeric(df$Money)
    data <- df
    
    switch(input$dist,money = data$Money,pin = data$Pin)
  })
  
  output$plot <- renderPlot({
    me.df = melt(dataReactive())
    me.df = rename(me.df, c(X1="Hour", X2="Qtype"))
    me.df
    # Get all data that should be visible to the current user.
    data <- me.df
    # Generate the sales plot
    p <- ggplot(data, aes(x1, x2))
    p <- p+geom_line()
    p <- p + xlim(0, 30)
   p <- p + ggtitle("September Sales Projections")
    p <- p + xlab("Day of Month")
    p <- p + ylab("Total Sales (USD)")
    print(p) 
    
    #hist(dataReactive())
  })

  # Generate a summary of the data
  output$summary <- renderPrint({
    summary(dataReactive())
  })
  output$barplot <- renderPlot({
    mtscaled <- as.matrix(scale(dataReactive()))
    heatmap(mtscaled,
            col = topo.colors(200, alpha=0.5),
            Colv=F, scale="none")
  })
  # Generate an HTML table view of the data
  output$table <- renderTable({
    data.frame(x=dataReactive())
  })
  
  clusters <- reactive({
    kmeans(dataReactive(), 3) #number of clusters
  })
  
  output$plot1 <- renderPlot({
    # A numerical vector of the form c(bottom, left, top, right) 
    # which gives the number of lines of margin to be specified on the four sides of the plot. 
    par(mar = c(5.1, 4.1, 0, 1))
    plot(dataReactive(),
         col = clusters()$cluster,
         pch = 20, cex = 2) # type / size
    points(clusters()$centers, pch = 3, cex = 3, lwd = 3)
  })
  
  
})
