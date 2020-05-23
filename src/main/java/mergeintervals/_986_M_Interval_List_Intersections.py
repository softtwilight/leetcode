def intervalIntersection(self, A: List[List[int]], B: List[List[int]]) -> List[List[int]]:
    i, j, res = 0, 0, []
    while i < len(A) and j < len(B):
        lo = max(A[i][0],B[j][0])
        hi = min(A[i][1],B[j][1])
        if lo <= hi:
            res.append([lo, hi])
        if A[i][1] < B[j][1]:
            i += 1
        elif A[i][1] < B[j][1]:
            j += 1
        else:
            i += 1
            j += 1
    return res