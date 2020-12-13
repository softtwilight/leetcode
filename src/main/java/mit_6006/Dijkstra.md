Dijkstra (G, W, s) //uses priority queue Q  
    Initialize (G, s)  
    S ← φ  
    Q ← V [G] //Insert into Q  
    while Q = φ  
        do u ← EXTRACT-MIN(Q) //deletes u from Q  
        S = S ∪ {u}  
        for each vertex v  Adj[u]  
        do RELAX (u, v, w) ← this is an implicit DECREASE KEY operation  