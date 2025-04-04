#Por causa do async e futures, é necessario executar cada exercicio individualmente em um if name==main no final do arquivo

#Header
import time
import random
from concurrent.futures import ProcessPoolExecutor, ThreadPoolExecutor
from multiprocessing import Pool

def getTime():
    return time.perf_counter()


#def getMem():
  #  return psutil.Process().memory_info().rss

def partitionArray(arr, n):
    length = len(arr)
    step = length // n  
    partitions = [arr[i * step : (i + 1) * step] for i in range(n - 1)] 
    partitions.append(arr[(n - 1) * step:])
    return partitions

#Exercicio 1.1
import asyncio
import aiohttp

async def downloadUrl(session, url):
    async with session.get(url) as response:
        content = await response.text()
        
async def processPartition(partition, session):
    return await asyncio.gather(*(downloadUrl(session, url) for url in partition))

async def ex11():
    urls = [
        "https://api.publicapis.org/entries",
        "https://api.open-meteo.com/v1/forecast?latitude=52.52&longitude=13.41&hourly=temperature_2m",
        "https://api.github.com",
        "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY",
        "https://api.agify.io?name=alex",
        "https://api.genderize.io?name=alex",
        "https://api.nationalize.io?name=alex",
        "https://api.zippopotam.us/us/90210",
        "https://api.spacexdata.com/v4/launches",
        "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin&vs_currencies=usd",
        "https://api.weather.gov/points/39.7456,-97.0892",
        "https://api.tvmaze.com/search/shows?q=breaking%20bad"
    ]
    for x in range(1, 9):
        time_avg = []
        urls_x = partitionArray(urls, x)
        for times in range(1,5):
                    async with aiohttp.ClientSession() as session:
                                start_time = getTime()
                                await asyncio.gather(*[await asyncio.to_thread(processPartition, partition, session) for partition in urls_x])
                                time_avg.append(getTime() - start_time)
        print(f"{sum(time_avg)/len(time_avg):.2f} segundos - {x} threads")

#asyncio.run(ex11())

# Exercicio 1.2
def randLinear(size=10000):
    return [random.randint(1, 100000) for _ in range(size)]

def randParallel(size=10000, num_threads=4):
    chunk_size = size // num_threads
    start_time = getTime()
    with ThreadPoolExecutor(max_workers=num_threads) as ex:
        chunks = list(ex.map(randLinear, [chunk_size] * num_threads))
        lst = []
        for chunk in chunks:
            lst.append(chunk)
        return lst

def ex12():
        time_avg = []
        for times in range(1,5):
            start_time = getTime()
            randLinear(1000000)
            time_avg.append(getTime() - start_time)
        print(f"{sum(time_avg)/len(time_avg):.2f} segundos - linear")
        
        for threads in range(2,6):
            time_avg = []
            for times in range(1,5):
                start_time = getTime()
                randParallel(1000000, times)
                time_avg.append(getTime() - start_time)
            print(f"{sum(time_avg)/len(time_avg):.2f} segundos - {threads} threads")

# Exercício 1.3: Processamento de Imagens Assíncrono
from PIL import Image, ImageFilter
import os

async def processImage(image_path, output_dir):
    img = Image.open(image_path)
    img = img.filter(ImageFilter.BLUR)
    img.save(os.path.join(output_dir, os.path.basename(image_path)))

async def processPartition(partition, out):
            return await asyncio.gather(*(processImage(img, out) for img in partition))
    
async def ex13():
    input_dir = "C:\\Users\\donke\\Pictures\\AoT RPG Fate"
    output_dir = os.path.join(input_dir, "out")
    os.makedirs(output_dir, exist_ok=True)
    images = [os.path.join(input_dir, img) for img in os.listdir(input_dir) if img.endswith(".png")]

    for x in range(1, 9):  
        time_avg = []
        image_partitions = partitionArray(images, x)

        for _ in range(1, 5):  
            start_time = getTime()


            await asyncio.gather(*[await asyncio.to_thread(processPartition, partition, output_dir) for partition in image_partitions])


            time_avg.append(getTime() - start_time)

        print(f"{sum(time_avg) / len(time_avg):.2f} segundos - {x} threads")

#asyncio.run(ex13())
def maxParallel(lst):
    with Pool() as pool:
        chunk_size = len(lst) // 4
        chunks = [lst[i:i + chunk_size] for i in range(0, len(lst), chunk_size)]
        results = pool.map(max, chunks)
    return max(results)

def maxLinear(lst):
            num = -9999999
            for x in lst:
                        if x > num:
                                    num = x
            return num



#Exercicio 2.1
import random
def quicksort(arr, pivot):
    if len(arr) < 2:
        return arr

    pivot_val= arr[pivot] 
    biggie = []
    smalls = []

    for i, x in enumerate(arr):
        if i == pivot: 
            continue
        if x >= pivot_val:
            biggie.append(x)
        else:
            smalls.append(x)

    pivot_s = 0
    pivot_b = 0
    if smalls:
        pivot_s = random.randint(0, len(smalls) - 1)
    if biggie:
        pivot_b = random.randint(0, len(biggie) - 1)

    return quicksort(smalls, pivot_s) + [pivot_val] + quicksort(biggie, pivot_b)

def ex21(size):
    arr = list(range(size))
    random.shuffle(arr)
    time_avg = []
    for _ in range(1, 5):  
            start_time = getTime()
            quicksort(arr, 0)
            time_avg.append(getTime() - start_time)
    print(f"Inicio do array - {sum(time_avg)/len(time_avg)*1000} segundos")
    time_avg = []
    for _ in range(1, 5):  
            start_time = getTime()
            quicksort(arr, len(arr)//2)
            time_avg.append(getTime() - start_time)
    print(f"Mediana do array - {sum(time_avg)/len(time_avg)*1000} segundos")
    time_avg = []
    for _ in range(1, 5):  
            start_time = getTime()
            quicksort(arr, len(arr)-1)
            time_avg.append(getTime() - start_time)
    print(f"Final do array - {sum(time_avg)/len(time_avg)*1000} segundos")

#Exercicio 2.2
class Student:
    def __init__(s, nome, nota):
        s.nome = nome
        s.nota = nota
    def __str__(s):
        return s.nome + " - " + str(s.nota)
    
def quicksortStudent(arr):
            if len(arr) < 2:
                        return arr
            pivot = random.randint(0, len(arr)-1)
            biggie = []
            smalls = []
            eq = []
            nota_t = arr[pivot].nota
            for x in arr:
                        if x.nota > nota_t:
                                    biggie.append(x)
                        elif x.nota < nota_t:
                                    smalls.append(x)
                        else:
                            eq.append(x)
            return quicksortStudent( smalls ) + eq + quicksortStudent( biggie )

def ex22():
    arr = []
    for i in range(80):
        arr.append( Student( "Aluno" + str(i), random.randint(0, 100)/10 ) )
    arr = quicksortStudent(arr)
    for s in arr:
        print(s)

#Exercicio 2.3
def partition(arr, l, r, pivot):
    pivot_v = arr[pivot]
    arr[pivot], arr[r] = arr[r], arr[pivot]  
    l_i = l
    for i in range(l, r):
        if arr[i] < pivot_v:
            arr[l_i], arr[i] = arr[i], arr[l_i]
            l_i += 1
    arr[r], arr[l_i] = arr[l_i], arr[r] 
    return l_i

def quickselect(arr, l, r, k):
            if l == r:
                        return arr[l]
                    
            pivot = random.randint(l, r)
            pivot = partition(arr, l, r, pivot)

            if k == pivot:
                        return arr[k]
            elif k < pivot:
                        return quickselect(arr, l, pivot-1, k)
            else:
                        return quickselect(arr, pivot+1, r, k)

def ex23():
    for n in range(1,6):
        for k in range(1,6):
            time_avg = []
            for _ in range(5):
                arr = list(range(pow(10,n)))
                random.shuffle(arr)
                start_time = getTime()
                quickselect(arr, 0, len(arr)-1, k)
                time_avg.append(getTime() - start_time)
            print(f"{k}-esimo menor elemento - {sum(time_avg)/len(time_avg)} segundos - {pow(10,n)} elementos")

#Exercicio 2.4
def ex24(size):
            arr = list(range(size))
            random.shuffle(arr)
            start_time = getTime()
            print("Mediana - ", quickselect(arr, 0, len(arr)-1, len(arr)//2))
            print(getTime() - start_time, "Segundos")

#Exercicio 3.1
class ListNode:
    def __init__(s, val, node=None):
        s.val = val
        s.node = node

class LinkedList:
    def __init__(s):
        s.inicio = None
    
    def addFirst(s, val):
        if s.inicio is None:
            s.inicio = ListNode(val)
        else:
            s.inicio = ListNode(val, s.inicio)
            
    def addLast(s, val):
        if s.inicio is None:
            s.addFirst(val)
        else:
            cur_node = s.inicio
            while cur_node.node is not None:
                cur_node = cur_node.node
            cur_node.node = ListNode(val)
            
    def removeVal(s, val):
        if s.inicio is None:
            return False
        
        if s.inicio.val == val:
            s.inicio = s.inicio.node
            return True
        
        prev_node = None
        cur_node = s.inicio
        while cur_node is not None:
            if cur_node.val == val:
                prev_node.node = cur_node.node
                return True
            prev_node = cur_node
            cur_node = cur_node.node
        
        return False

    def find_i(s, val):
        if s.inicio is None:
            return -1
        
        if s.inicio.val == val:
            return 0
        
        cur_node = s.inicio
        i = 0
        while cur_node is not None:
            if cur_node.val == val:
                return i
            cur_node = cur_node.node
            i+=1
        
        return -1

    def reverse(s):
        reversed_list = LinkedList()
        cur_node = s.inicio
        while cur_node is not None:
            reversed_list.addFirst(cur_node.val)
            cur_node = cur_node.node
        return reversed_list
        
    
    def __str__(s):
        elements = []
        cur_node = s.inicio
        while cur_node is not None:
            elements.append(str(cur_node.val))
            cur_node = cur_node.node
        return ", ".join(elements)

def ex31():
    for size in range(1, 6):
        print(f"\n{size*2} elementos")
        ll = LinkedList()
        for i in range(size):
            ll.addFirst(i*10 + 10)
            print(ll)
        for i in range(size):
            ll.addLast(i*5 + 5)
            print(ll)
        for i in range(size):
            ll.removeVal(i*10 + 10)
            print(ll)
        for i in range(size):
            ll.removeVal(i*5 + 5)
            print(ll)

#Exercicio 3.2
class DListNode:
    def __init__(s, val, node_l=None, node_r=None):
        s.val = val
        s.node_l = node_l
        s.node_r = node_r

class DLinkedList:
    def __init__(s):
        s.inicio = None
        s.fim = None  
    
    def addFirst(s, val):
        new_node = DListNode(val, None, s.inicio)
        if s.inicio is not None:
            s.inicio.node_l = new_node
        s.inicio = new_node
        if s.fim is None:  
            s.fim = new_node

    def addLast(s, val):
        if s.inicio is None:
            s.addFirst(val)
        else:
            new_node = DListNode(val, s.fim, None)
            s.fim.node_r = new_node
            s.fim = new_node
    
    def removeAt(s, pos):
        if s.inicio is None:
            return False
        
        if pos == 0:  
            s.inicio = s.inicio.node_r
            if s.inicio is not None:
                s.inicio.node_l = None
            else:
                s.fim = None
            return True

        cur_node = s.inicio
        index = 0
        while cur_node is not None and index < pos:
            cur_node = cur_node.node_r
            index += 1
        
        if cur_node is None:  
            return False

        if cur_node.node_r is not None:
            cur_node.node_r.node_l = cur_node.node_l
        else:  
            s.fim = cur_node.node_l  

        if cur_node.node_l is not None:
            cur_node.node_l.node_r = cur_node.node_r
        
        return True
    
    def reverse(s):
        elements = []
        cur_node = s.fim
        while cur_node is not None:
            elements.append(str(cur_node.val))
            cur_node = cur_node.node_l
        return " <- ".join(elements)

    def insertionSort(s):
        if s.inicio is None:
            return
        
        cur = s.inicio.node_r
        while cur is not None:
            key = cur.val
            prev = cur.node_l
            
            while prev is not None and prev.val > key:
                prev.node_r.val = prev.val
                prev = prev.node_l
            
            if prev is None:
                s.inicio.val = key
            else:
                prev.node_r.val = key
            
            cur = cur.node_r

    def merge(s,s2):
        if s.inicio is None: 
            s.inicio = s2.inicio
            s.fim = s2.fim
            return
        
        if s2.inicio is None:  
            return

        s.fim.node_r = s2.inicio
        s2.inicio.node_l = s.fim

        s.fim = s2.fim

        s.insertionSort()
        
    
    def __str__(s):
        elements = []
        cur_node = s.inicio
        while cur_node is not None:
            elements.append(str(cur_node.val))
            cur_node = cur_node.node_r
        return " -> ".join(elements)

def ex32():
    for size in range(1, 6):
        print(f"\n{size*2} elementos")
        ll = DLinkedList()
        for i in range(size):
            ll.addFirst(i*10 + 10)
            print(ll)
        for i in range(size):
            ll.addLast(i*5 + 5)
            print(ll)
        for i in range(size+1):
            ll.removeAt(i)
            print(ll)
        print(ll.reverse())

def ex33():
    for size in range(1, 6):
        print(f"\n{size*2} elementos")
        ll = LinkedList()
        for i in range(size):
            ll.addFirst(i*10 + 10)
        for i in range(size):
            ll.addLast(i*5 + 5)
        print(ll)
        for i in range(size):
            print(f"Procurando {i*5 + 5}: {ll.find_i(i*5 + 5)}")
        print(ll.reverse())

def ex34():
    for size in range(1, 6):
        print(f"\n{size*2} elementos")
        ll = DLinkedList()
        for i in range(size):
            ll.addFirst(i*10 + 10)
        for i in range(size):
            ll.addLast(i*5 + 5)
        ll.insertionSort()
        print(ll)
        
        ll2 = DLinkedList()
        for i in range(size):
            ll2.addFirst(i*4 + 4)
        for i in range(size):
            ll2.addLast(i*3 + 25)
        print(ll2)
        ll2.merge(ll)
        print(ll2)


#Exercicio 4.1
def fatorial(n):
            if n <= 1:
                        return 1
            return n * fatorial(n-1)

def ex41():
            for i in range(1, 205, 4):
                        time_avg = []
                        for _ in range(5):
                                    start_time = getTime()
                                    fatorial(i)
                                    time_avg.append(getTime() - start_time)
                        print(f"Fatorial de {i} - {sum(time_avg)/len(time_avg)*10000}")

#ex41()

#Exercicio 4.2
def fibonacci(n):
            if n <= 1:
                        return 1
            return fibonacci(n-1) + fibonacci(n-2)

def fibonacciMemo(n, cache):
            if n <= 1:
                return 1
            
            k = str(n)
            
            if k in cache:
                return cache[k]
            
            res = fibonacciMemo(n-1, cache) + fibonacciMemo(n-2, cache)
            cache[k] = res
            return res

def ex42():
            for i in range(1, 20):
                    time_avg = []
                    for _ in range(5):
                                cache = dict()
                                start_time = getTime()
                                fibonacci(i)
                                time_avg.append(getTime() - start_time)
                    print(f"Fibonacci na posição {i} - {sum(time_avg)/len(time_avg)*10000}")

            for i in range(1, 20):
                        time_avg = []
                        for _ in range(5):
                                    cache = dict()
                                    start_time = getTime()
                                    fibonacciMemo(i, cache)
                                    time_avg.append(getTime() - start_time)
                        print(f"Fibonacci com memorização na posição {i} - {sum(time_avg)/len(time_avg)*10000}")

                        
# Exercício 5.1: Soma de Elementos em uma Lista
import concurrent.futures as fut

def somaParalela(lst):
    chunk_size = len(lst) // os.cpu_count()
    start_time = getTime()
    with fut.ThreadPoolExecutor(max_workers=os.cpu_count()) as exc:
        fut_vec = {exc.submit(sum, lst[chunk_i:chunk_i + chunk_size]): chunk_i for chunk_i in range(0, len(lst), chunk_size)}

        results = []
        for future in fut.as_completed(fut_vec):
            results.append(future.result())
        print(getTime()-start_time, "segundos -  Soma paralela")
        return sum(results)

def somaLinear(lst):
    soma = 0
    start_time = getTime()
    for i in lst:
        soma += i
    print(getTime()-start_time, "segundos -  Soma linear")
    return soma

def ex51():
    for i_n in range(4, 9):
        i = 10**i_n+1
        print("\n", i, "elementos")
        print(somaParalela(list(range(1, i))))
        print(somaLinear(list(range(1, i))))



# Exercício 5.2: Busca em Árvore Binária
from multiprocessing import Process, Value

def searchSubtree(node, target, found):
    if node is None or found.value:
        return
    if node.value == target:
        found.value = True
        return
    searchSubtree(node.left, target, found)
    searchSubtree(node.right, target, found)
        
class TreeNode:
    def __init__(s, value):
        s.value = value
        s.left = None
        s.right = None
        
    def add(s, value):
        if value < s.value:
            if s.left is None:
                s.left = TreeNode(value)
            else:
                s.left.add(value)
        elif value > s.value:
            if s.right is None:
                s.right = TreeNode(value)
            else:
                s.right.add(value)

def parallelSearch(root, target):
    found = Value('b', False)
    left_proc = Process(target=searchSubtree, args=(root.left, target, found))
    right_proc = Process(target=searchSubtree, args=(root.right, target, found))

    left_proc.start()
    right_proc.start()
    left_proc.join()
    right_proc.join()

    return found.value

def ex52():
              for i in range(1, 9):
                  node_n = pow(2,i)
                  root = TreeNode(1)
                  for node in list(range(node_n)):
                     root.add(node)
                  time_avg = []
                  for _ in range(5):
                        start_time = getTime()
                        parallelSearch(root, node_n)
                        time_avg.append(getTime() - start_time)
                  print(f"{node_n} Nodes - {sum(time_avg)/len(time_avg)} segundos")         



#Exercicio 5.3
def mergeStep(l_arr, r_arr):
    left_i = 0
    right_i = 0
    res = []
    while left_i < len(l_arr) and right_i < len(r_arr):
        if l_arr[left_i] < r_arr[right_i]:
            res.append(l_arr[left_i])
            left_i += 1
        else:
            res.append(r_arr[right_i])
            right_i += 1
    
    if left_i < len(l_arr):
        res.extend(l_arr[left_i:])
    
    if right_i < len(r_arr):
        res.extend(r_arr[right_i:])
    return res
    
def mergeSort(arr):
    if len(arr) <= 1:
        return arr
    middle = len(arr)//2
    left_arr = arr[:middle]
    right_arr = arr[middle:]
    return mergeStep(mergeSort(left_arr), mergeSort(right_arr))

def mergeSortParallel(arr):
    if len(arr) <= 1:
        return arr

    middle = len(arr) // 2
    left_arr = arr[:middle]
    right_arr = arr[middle:]
    with Pool(processes=2) as pool:
        sorted_arr = pool.map(mergeSort, [left_arr, right_arr])
    return mergeStep(sorted_arr[0], sorted_arr[1])

def ex53(size):
            lst = list(range(size))
            random.shuffle(lst)
            time_avg = []
            for _ in range(5):
                        start_time = getTime()
                        mergeSort(lst)
                        time_avg.append(getTime() - start_time)
            print(f"Linear - {sum(time_avg)/len(time_avg)} segundos")
            
            time_avg = []
            for _ in range(5):
                        start_time = getTime()
                        mergeSortParallel(lst)
                        time_avg.append(getTime() - start_time)
            print(f"Paralelo - {sum(time_avg)/len(time_avg)} segundos")       
                        

def maxParallel(lst):
    with Pool() as pool:
        chunk_size = len(lst) // 4
        chunks = [lst[i:i + chunk_size] for i in range(0, len(lst), chunk_size)]
        results = pool.map(max, chunks)
    return max(results)

def maxLinear(lst):
            num = -9999999
            for x in lst:
                        if x > num:
                                    num = x
            return num

def ex54(size):
            large_list = list(range(1, size))
            random.shuffle(large_list)
            time_avg = []
            for _ in range(5):
                        start_time = getTime()
                        maxLinear(large_list)
                        time_avg.append(getTime()-start_time)
            print(f"Maximo linear: {maxLinear(large_list)} - {sum(time_avg)/len(time_avg)} segundos")
            time_avg = []
            for _ in range(5):
                        start_time = getTime()
                        maxParallel(large_list)
                        time_avg.append(getTime()-start_time)
            print(f"Maximo paralelo: {maxParallel(large_list)} - {sum(time_avg)/len(time_avg)} segundos")

if __name__ == '__main__':
            ex21(8)
            #chame a função de um exercicio aqui

            
