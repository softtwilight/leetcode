## pseudocode of quickSelect

recursive version :
```
function select(list, left, right, k) is
    if left = right then   // If the list contains only one element,
        return list[left]  // return that element
    pivotIndex  := ...     // select a pivotIndex between left and right,
                           // e.g., left + floor(rand() % (right − left + 1))
    pivotIndex  := partition(list, left, right, pivotIndex)
    // The pivot is in its final sorted position
    if k = pivotIndex then
        return list[k]
    else if k < pivotIndex then
        return select(list, left, pivotIndex − 1, k)
    else
        return select(list, pivotIndex + 1, right, k) 
```

iterative version: 
```
function select(list, left, right, k) is
    loop
        if left = right then
            return list[left]
        pivotIndex := ...     // select pivotIndex between left and right
        pivotIndex := partition(list, left, right, pivotIndex)
        if k = pivotIndex then
            return list[k]
        else if k < pivotIndex then
            right := pivotIndex − 1
        else
            left := pivotIndex + 1
```

function partition(list, left, right, pivotIndex) is
    pivotValue := list[pivotIndex]
    swap list[pivotIndex] and list[right]  // Move pivot to end
    storeIndex := left
    for i from left to right − 1 do
        if list[i] < pivotValue then
            swap list[storeIndex] and list[i]
            increment storeIndex
    swap list[right] and list[storeIndex]  // Move pivot to its final place
    return storeIndex
    
