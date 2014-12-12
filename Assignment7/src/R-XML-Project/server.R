
# This is the server logic for a Shiny web application.
# You can find out more about building applications with Shiny here:
#
# http://shiny.rstudio.com
# http://www.generatedata.com/

library(shiny)
library(shinyapps)
library(XML)
library(ggplot2)
library(rCharts)
library(reshape2)
library(tm)
library(wordcloud)
library(plyr)
library(maps)
library(mapdata)
library(rworldmap)
library(ggmap)
library(maptools)
library(gridSVG)
library(Cairo)

shinyServer(function(input, output) {
  
  dataReactive <- reactive({
    xmlfile <- "dataDec-1-2014.xml"
    book = xmlParse(xmlfile)
    # xml Tree Parse -> tree data
    df = xmlToDataFrame(book, stringsAsFactors=FALSE)
    
    xmltop = xmlRoot(book) #gives content of root
    xmltop
    class(xmltop)
    xmlName(xmltop[[5]]) # records
    xmlSApply(xmltop[[6]], xmlName)
    #Madhu2012=ldply(xmlToList(xmlfile), data.frame)
    #xmlEventParse(xmlfile) #SAX Parser
    class(df$PIN)
    df$PIN = as.numeric(df$PIN)
    class(df$PIN)
    class(df$Money)
    #df$Money = gsub("\\.", "", df$Money) # DELETE .
    df$Money = as.numeric(df$Money)
    
    switch(input$dist,money = df$Money,pin = df$PIN)
  })
  
  output$plot <- renderPlot({
    me.df = melt(dataReactive())
    me.df
    # Generate the sales plot
    p <- ggplot(me.df, aes(x=value))
    p <- p + geom_dotplot()
    p <- p + scale_y_continuous(breaks =NULL)
    p <- p + ggtitle("Y is meaningless")
    p <- p + xlab(input$dist)
    print(p) 
    # SVG
    Cairo(500,500,file="cairo_2.svg",type="svg",bg="transparent",pointsize=5, 
          units="in",dpi="auto")
    print(p)
    dev.off()
    
    # another possibility
    #hist(dataReactive())
  })
  
  output$wp1 <- renderPlot({
    cou <- df$Country
    lat <- df$LatLon
    map("world", cou, fill=TRUE, col="white", bg="lightblue", mar=c(0,0,0,0))
    #print(mp)
    
  })
  
  output$cloud <- renderPlot({
    myCorpus = Corpus(VectorSource(df$Region))
    myCorpus = tm_map(myCorpus, content_transformer(tolower))
    myCorpus = tm_map(myCorpus, removePunctuation)
    myCorpus = tm_map(myCorpus, removeNumbers)
    myCorpus = tm_map(myCorpus, removeWords,
                      c(stopwords("SMART"), 
                        "iowa", "island", "dazur", "munster", "rhinewestphalia","vic", "pays", 
                        "san", "sao", "wie", "pie", "loire", "sic", "nadu"))
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
  
  output$betterTable <- renderDataTable({
    df
  })
  
  # Generate a summary of the data
  output$summary <- renderPrint({
    summary(dataReactive())
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
  
  output$myChart <- renderChart({
    names(iris) = gsub("\\.", "", names(iris))
    cars <- c(1:20)
    yc <- c(4:23)
    dt <- data.frame(cars, yc)
    dt
    # color = "Species", facet = "Species", type = 'point'
    p1 <- mPlot(x="cars", y="yc", data = dt, type = 'Line')
    
    p1$addParams(dom = 'myChart')
    p1$set(pointSize = 0, lineWidth = 1)
    p1
    return(p1)
        
    
    
    
  })
  
})
