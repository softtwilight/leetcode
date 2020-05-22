def leastInterval(self, tasks, N):
	task_map = list(collections.Counter(tasks).values())
	M = max(task_map)
	M_count = task_map.count(M)
	return max(len(tasks), (M - 1) * (N + 1) + M_count)