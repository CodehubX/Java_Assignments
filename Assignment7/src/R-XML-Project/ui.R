
# This is the user-interface definition of a Shiny web application.
# You can find out more about building applications with Shiny here:
#
# http://shiny.rstudio.com
#

library(shiny)

shinyUI(fluidPage(

  # Application title
  titlePanel("XML Visualization"),
  
  sidebarLayout(
    sidebarPanel(
      radioButtons("dist", "Data inside data frame:",
                   c("pin" = "pin","money" = "money")
      )
    ),
  
  
    mainPanel(
      tabsetPanel(type = "tabs", 
                tabPanel("Plot", plotOutput("plot")), 
                tabPanel("Summary", verbatimTextOutput("summary")), 
                tabPanel("Table", tableOutput("table")),
                tabPanel("WordCloud", plotOutput("wordcloud")),
                tabPanel("K-Means", plotOutput("plot1"))
      
      )
    )
  )
))
