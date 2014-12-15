hc <- hclust(dist(df$Tree))

# plot basic tree
plot(as.phylo(hc), cex = 0.9, label.offset = 1)
# cladogram
plot(as.phylo(hc), type="cladogram", cex = 0.9, label.offset = 1)


plot(as.phylo(hc), type = "fan", tip.color = hsv(runif(15, 0.65, 0.95), 1, 1, 0.7), 
     edge.color = hsv(runif(10, 0.65, 0.75), 1, 1, 0.7), edge.width = runif(20, 
    0.5, 3), use.edge.length = TRUE, col = "gray80")


p <- makeDepGraph(df$Names[1:60], availPkgs=df$Tree[1:60])

plotColours <- c("grey80", "orange")
topLevel <- as.numeric(V(p)$name %in% tags)

par(mai=rep(0.25, 4))

set.seed(50)
vColor <- plotColours[1 + topLevel]
plot(p, vertex.size=8, edge.arrow.size=0.5, 
     vertex.label.cex=0.7, vertex.label.color="black", 
     vertex.color=vColor)
legend(x=0.9, y=-0.9, legend=c("Dependencies", "Initial list"), 
       col=c(plotColours, NA), pch=19, cex=0.9)
text(0.9, -0.75, expression(xts %->% zoo), adj=0, cex=0.9)
text(0.9, -0.8, "xts depends on zoo", adj=0, cex=0.9)
title("Package dependency graph")
