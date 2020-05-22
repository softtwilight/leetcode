class Solution:
    def findMinArrowShots(self, points: List[List[int]]) -> int:
        res, arrow = 0, -float('inf')
        points = sorted(points, key = lambda p:p[1])
        for start, end in points:
            if start > arrow: 
                res += 1
                arrow = end
        return res 