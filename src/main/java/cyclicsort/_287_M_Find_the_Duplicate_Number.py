class Solution:
    # cyclic sort
    def findDuplicate(self, nums: List[int]) -> int:
        for i in range(len(nums)):
            while i + 1 != nums[i]:
                if nums[i] == nums[nums[i] - 1]:
                    return nums[i]
                tmp = nums[i]
                nums[i], nums[tmp - 1] = nums[tmp - 1], nums[i]
        return 1