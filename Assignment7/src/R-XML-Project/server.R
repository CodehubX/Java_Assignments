
# This is the server logic for a Shiny web application.
# You can find out more about building applications with Shiny here:
# 
# library(mapdata)
# library(rworldmap)
# library(ggmap)
# library(maptools)
# http://shiny.rstudio.com
# http://www.generatedata.com/

library(shiny)
library(XML)
library(ggplot2)
library(rCharts)
library(reshape2)
library(tm)
library(wordcloud)
library(maps)
library(Cairo)

shinyServer(function(input, output) {
  
  xmlfile <- "dataDec-1-2014.xml"
  # Parses an XML or HTML file or string containing XML/HTML content, 
  # and generates an R structure representing the XML/HTML tree. 
  # Return value is, an object of (S3) class XMLDocument
  # Same to
  file.xml <- xmlTreeParse(xmlfile)
  #file.xml = xmlParse(xmlfile)
  class(file.xml)
  
  # xml Tree Parse -> tree data
  df = xmlToDataFrame(xmlfile, stringsAsFactors=FALSE)
  
  # Analysis
  # print all records of our XML file (!not view(df)-> console)
  xmlroot = xmlRoot(file.xml) #gives content of root
  xmlroot
  class(xmlroot)
  xmlName(xmlroot[[5]]) # records
  
  #
  # for each xmlroot element #6, print all names of elements
  xmlSApply(xmlroot[[6]], xmlName)
  # Analysis ende
  
  # Madhu2012=ldply(xmlToList(file.xml), data.frame)
  # xmlEventParse(file.xml) #SAX Parser
  
  class(df$PIN)
  df$PIN = as.numeric(df$PIN)
  class(df$PIN)
  
  class(df$Money)
  # Not used anymore
  # df$Money = gsub("\\.", "", df$Money) # DELETE .
  df$Money = as.numeric(df$Money)
  class(df$Money)
  
  
  # A reactive expression (a function) is a expression whose result will change over time.
  dataReactive <- reactive({
    switch(input$dist, money = df$Money,pin = df$PIN)
  })
  
  # First Plot
  output$plot <- renderPlot({
    print(class(dataReactive())) # numeric
    # Convert object into a molten data frame, here it is a list
    me.df = melt(dataReactive()) #refer to dataReactive func. above
    
    #print(me.df)
    #print(sort(df$PIN, decreasing = TRUE))
    
    # Generate the dot plot, using ggplot2
    p <- ggplot(me.df, aes(x=value))
    p <- p + geom_dotplot()
    p <- p + scale_y_continuous(breaks = NULL)
    p <- p + ggtitle("Y is meaningless")
    p <- p + xlab(input$dist)
    print(p) 
    
    # and store it in SVG
    Cairo(500,500,file="cairo_2.svg",type="svg",bg="transparent",pointsize=5, 
          units="in",dpi="auto")
    print(p)
    dev.off() #closes x11()
    
    # Another possibility
    #hist(dataReactive())
  })
  
  #Plot map (of countries)
  output$wp1 <- renderPlot({
    cou <- df$Country
    #lat <- df$LatLon
    map("world", cou, fill=TRUE, col="white", bg="lightblue", mar=c(0,0,0,0))
  })
  
  # Worldcloud for regions
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
    
    # diplay them
    v
    # some color
    pal <- brewer.pal(11,"RdYlBu")
    pal <- pal[-(1:4)]
    
    wordcloud(names(v), v, c(4,.2),10,,FALSE,,.05,pal)
    
  })
  
  # Generate an HTML table view of the data - the basic one
  output$table <- renderTable({
    data.frame(x=dataReactive())
  })
  
  # Better Table from Data Table JavaScript
  output$betterTable <- renderDataTable({
    df
  })
  
  # Generate a summary of the data
  output$summary <- renderPrint({
    summary(dataReactive())
  })
  
  # k-means clustering on a data matrix
  # either the number of clusters, say k, or a set of initial (distinct) cluster centres. 
  # If a number, a random set of (distinct) rows in x is chosen as the initial centres.
  clusters <- reactive({
    kmeans(dataReactive(),8) #number of clusters
  })
  
  output$plot1 <- renderPlot({
    # A numerical vector of the form c(bottom, left, top, right) 
    # which gives the number of lines of margin to be specified on the four sides of the plot. 
    # par(mar = c(5.1, 4.1, 0, 1))
    plot(dataReactive(),col = clusters()$cluster,pch = 16, cex = 2) # type / size
    points(clusters()$centers, pch = 3, cex = 3, lwd = 3)
  })
  
  # Our SVG Chart with data from below (NOT XML DATA)
  output$myChart <- renderChart({
    
    #create dataframe
    cars <- c(1:20)
    cars
    yc <- c(1:20)
    dt <- data.frame(cars, yc)
    dt
    
    # plot using MorrisJS
    p1 <- mPlot(x="cars", y="yc", data = dt, type = 'Line')
    p1$addParams(dom = 'myChart')
    p1$set(pointSize = 0, lineWidth = 1)
    return(p1)
  })
  
  # Our SVG Chart with data XML
  output$second <- renderChart({
    #add new column with row index
    df$index<-as.numeric(rownames(df))
    #df
    p2 <- mPlot(x="Money", y="index", data = df, type = "Line")
    p2$addParams(dom = 'second')
    return(p2)
  })
  
})
