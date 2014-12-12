
# This is the user-interface definition of a Shiny web application.
# You can find out more about building applications with Shiny here:
#
# http://shiny.rstudio.com
#

library(shiny)
library(rCharts)

shinyUI(fluidPage(
  
  # Application title
  titlePanel("XML Visualization"),
  
  fluidRow(
    column(12,
           radioButtons("dist", "Data inside data frame:",
                        c("pin" = "pin","money" = "money")
           ),
           navlistPanel(            
             "Header B",
             tabPanel("Plot", plotOutput("plot")), 
             tabPanel("Summary", verbatimTextOutput("summary")), 
             tabPanel("Table", tableOutput("table")),
             tabPanel("DataFrame Table",  dataTableOutput(outputId="betterTable")),
             tabPanel("WordCloud", plotOutput("cloud")),
             tabPanel("WorldMap", plotOutput("wp1")),
             tabPanel("K-Means", plotOutput("plot1")),
             well=TRUE
           ), 
           mainPanel(showOutput("myChart", "morris"))
    )
  )
))
