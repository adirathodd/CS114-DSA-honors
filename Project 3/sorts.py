array = [10, 4, 5, 1, 9, 8, 3, 6, 7, 2]

def quicksort(array):
    if len(array) <= 1:
        return array
    
    pivot = len(array) // 2 - 1

    array[pivot], array[-1] = array[-1], array[pivot]
    l, r = 0, len(array) - 2

    while l <= r:
        if array[l] < array[-1]:
            l += 1
        elif array[r] >= array[-1]:
            r -= 1
        else:
            array[l], array[r] = array[r], array[l]
            r -= 1
            l += 1
    
    array[l], array[-1] = array[-1], array[l]
    left = quicksort(array[:l])
    right = quicksort(array[l+1:])
    return left + [array[l]] + right 

def merge(a, b):
    res = []

    while a and b:
        if a[0] < b[0]:
            res.append(a.pop(0))
        else:
            res.append(b.pop(0))
    
    while a:
        res.append(a.pop(0))
    
    while b:
        res.append(b.pop(0))
    
    return res

def mergesort(array):
    if len(array) <= 1:
        return array

    mid = len(array) // 2
    left = mergesort(array[:mid])
    right = mergesort(array[mid:])

    return merge(left, right)

print(f"Quicksort: {quicksort(array)}")
print(f"Mergesort: {mergesort(array)}")