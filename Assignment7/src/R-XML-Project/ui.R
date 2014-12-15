
# This is the user-interface definition of a Shiny web application.
# You can find out more about building applications with Shiny here:
#
# http://shiny.rstudio.com
#

library(shiny)
library(rCharts)

shinyUI(fluidPage(
  
  # Application title
  titlePanel("XML Visualization - Assignment 7"),
  
  
  sidebarLayout(
    
    sidebarPanel(
      radioButtons("dist", "Data inside data frame:",
                   c("pin" = "pin","money" = "money")
      )
    ),
    mainPanel(
      tabsetPanel(
        tabPanel("Plot", plotOutput("plot")), 
        tabPanel("Summary", verbatimTextOutput("summary")), 
        tabPanel("Table", tableOutput("table")),
        tabPanel("DataFrame Table",  dataTableOutput(outputId="betterTable")),
        tabPanel("WordCloud", plotOutput("cloud")),
        tabPanel("WorldMap", plotOutput("wp1")),
        tabPanel("K-Means", plotOutput("plot1")),
        tabPanel("Test", showOutput("myChart", "morris"))
      )
    )
  )
))
