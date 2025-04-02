import time

OUT_PATH = "./output_cienciacomp_tp1.txt"

def swap(arr, a, b):
    #Troca posição a <-> b no vetor arr
    temp = arr[a]
    arr[a] = arr[b]
    arr[b] = temp


def getTime():
    #Tempo em segundos
    return time.perf_counter()


def bubbleSort(arr):
    noswap = False
    while not noswap:
        noswap = True
        for i in range(1, len(arr)):
            if arr[i] < arr[i-1]:
                swap(arr, i, i-1)
                noswap = False
    return arr


def selectionSort(arr):
    for i in range(len(arr)):
        cur_min = arr[i]
        for j in range(i+1, len(arr)):
            if arr[j] < cur_min:
                swap(arr, i, j)
            else:
                cur_min = arr[j]
    return arr


def insertionSort(arr):
    for i in range(len(arr)):
        k = arr[i]
        j = i-1

        while (j >= 0 and arr[j] > k):
            arr[j+1] = arr[j]
            j -= 1

        arr[j + 1] = k
    return arr


def doSortFuncOnFile(path, func, out, times=10, limit=-1):
    f = open(path, "r") #arquivo entrada
    fo = open(out, "w") #arquivo saida
    time_avg = [] #tempos calculados
    for i in range(times):
        start_time = getTime()
        fo.write("".join(func(f.readlines()[:limit])))
        #executa a função func nos conteudos de f até a linha limit
        time_avg.append(getTime() - start_time)
    print(sum(time_avg)/len(time_avg), " segundos") #média de tempo
    f.close()
    fo.close()


print("Bubble Sort (10k)")
doSortFuncOnFile(OUT_PATH, bubbleSort, "./output_tp1/out_bubble.txt")
print("Bubble Sort (5k)")
doSortFuncOnFile(OUT_PATH, bubbleSort, "./output_tp1/out_bubble2.txt", 10, 5000)
print("Bubble Sort (2k)")
doSortFuncOnFile(OUT_PATH, bubbleSort, "./output_tp1/out_bubble3.txt", 10, 2000)
print("Bubble Sort (1k)")
doSortFuncOnFile(OUT_PATH, bubbleSort, "./output_tp1/out_bubble4.txt", 10, 1000)
print("Bubble Sort (500)")
doSortFuncOnFile(OUT_PATH, bubbleSort, "./output_tp1/out_bubble5.txt", 10, 500)
print("Bubble Sort (100)")
doSortFuncOnFile(OUT_PATH, bubbleSort, "./output_tp1/out_bubble6.txt", 10, 100)
print("--------------------------------------------")
print("Selection Sort (10k)")
doSortFuncOnFile(OUT_PATH, selectionSort, "./output_tp1/out_select.txt")
print("Selection Sort (5k)")
doSortFuncOnFile(OUT_PATH, selectionSort, "./output_tp1/out_select2.txt", 10, 5000)
print("Selection Sort (2k)")
doSortFuncOnFile(OUT_PATH, selectionSort, "./output_tp1/out_select3.txt", 10, 2000)
print("Selection Sort (1k)")
doSortFuncOnFile(OUT_PATH, selectionSort, "./output_tp1/out_select4.txt", 10, 1000)
print("Selection Sort (500)")
doSortFuncOnFile(OUT_PATH, selectionSort, "./output_tp1/out_select5.txt", 10, 500)
print("Selection Sort (100)")
doSortFuncOnFile(OUT_PATH, selectionSort, "./output_tp1/out_select6.txt", 10, 100)
print("--------------------------------------------")
print("Insertion Sort (10k)")
doSortFuncOnFile(OUT_PATH, insertionSort, "./output_tp1/out_insert.txt")
print("Insertion Sort (5k)")
doSortFuncOnFile(OUT_PATH, insertionSort, "./output_tp1/out_insert2.txt", 10, 5000)
print("Insertion Sort (2k)")
doSortFuncOnFile(OUT_PATH, insertionSort, "./output_tp1/out_insert3.txt", 10, 2000)
print("Insertion Sort (1k)")
doSortFuncOnFile(OUT_PATH, insertionSort, "./output_tp1/out_insert4.txt", 10, 1000)
print("Insertion Sort (500)")
doSortFuncOnFile(OUT_PATH, insertionSort, "./output_tp1/out_insert5.txt", 10, 500)
print("Insertion Sort (100)")
doSortFuncOnFile(OUT_PATH, insertionSort, "./output_tp1/out_insert6.txt", 10, 100)
