
# This is the server logic for a Shiny web application.
# You can find out more about building applications with Shiny here:
#
# http://shiny.rstudio.com
# http://www.generatedata.com/

library(shiny)
library(XML)
library(ggplot2)

shinyServer(function(input, output) {
  
  data <- reactive({
    book = xmlParse("dataNov-29-2014.xml")
    df = xmlToDataFrame("dataNov-29-2014.xml")
    df$Money = as.numeric(df$Money)
    data <- df
    
    dist <- switch(input$dist,money = data$Money,pin = data$Pin)
  })
  
  output$plot <- renderPlot({
    dist <- input$dist 
    plot(data())
  })

  # Generate a summary of the data
  output$summary <- renderPrint({
    summary(data())
  })
  
  # Generate an HTML table view of the data
  output$table <- renderTable({
    data.frame(x=data())
  })
  
})
