
# This is the server logic for a Shiny web application.
# You can find out more about building applications with Shiny here:
#
# http://shiny.rstudio.com
# http://www.generatedata.com/

library(shiny)
library(XML)
library(ggplot2)
library(reshape2)
library(tm)
library(wordcloud)
library(plyr)

shinyServer(function(input, output) {
  
  dataReactive <- reactive({
    book = xmlParse("dataDec-1-2014.xml")
    df = xmlToDataFrame(book, stringsAsFactors=FALSE)
    class(df$PIN)
    df$PIN = as.numeric(df$PIN)
    class(df$PIN)
    class(df$Money)
    #df$Money = gsub("\\.", "", df$Money) # DELETE .
    df$Money = as.numeric(df$Money)
    data <- df
    
    switch(input$dist,money = data$Money,pin = data$PIN)
  })
  
  output$plot <- renderPlot({
    me.df = melt(dataReactive())
    me.df
    # Get all data that should be visible to the current user.
    data <- me.df
    # Generate the sales plot
    p <- ggplot(data, aes(x=value))
    p <- p + geom_dotplot()
    p <- p + scale_y_continuous(breaks =NULL)
    p <- p + ggtitle("Y is meaningless")
    p <- p + xlab(input$dist)
    print(p) 
    
    #hist(dataReactive())
  })

  # Generate a summary of the data
  output$summary <- renderPrint({
    summary(dataReactive())
  })
  
  output$wordcloud <- renderPlot({
    myCorpus = Corpus(VectorSource(data$Region))
    myCorpus = tm_map(myCorpus, content_transformer(tolower))
    myCorpus = tm_map(myCorpus, removePunctuation)
    myCorpus = tm_map(myCorpus, removeNumbers)
    myCorpus = tm_map(myCorpus, removeWords,
                      c(stopwords("SMART"), "iowa", "island", "dazur", "munster", "rhinewestphalia","vic", "pays", "san", "sao", "wie", "pie", "loire", "sic", "nadu"))
    
    myDTM = TermDocumentMatrix(myCorpus, control = list(minWordLength = 1))
    #class(myDTM)
    m = as.matrix(myDTM)
    v <- sort(rowSums(m), decreasing = TRUE)
    wordcloud(names(v), v, min.freq = 15)
    #print(w)    
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
