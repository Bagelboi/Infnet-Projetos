import time
import psutil
import sys

OUT_PATH = "./output_cienciacomp_tp1.txt"
TIMES = 40


def getTime():
    return time.perf_counter()


def getMem():
    return psutil.Process().memory_info().rss


class DanStack:
    top = 0
    e = []

    def get(s, k):
        if k < s.top and k > -1:
            return s.e[k]
        return None

    def add(s, el):
        s.e.append(el)
        s.top += 1

    def remove(s, i=-1):
        i = min(i, max(0, s.top))
        if i < 0:
            i = s.top
        i = max(i - 1, 0)
        s.e = s.e[:i] + s.e[i+1:]
        s.top -= 1

    def clear(s):
        s.e.clear()
        s.top = 0

    def show(s):
        print(s.e, f" - {s.top} elementos")


class DanQueue:
    e = []
    size = 0

    def get(s, k):
        if k < s.size and k > -1:
            return s.e[k]
        return None

    def add(s, el):
        s.e = [el] + s.e
        s.size += 1

    def remove(s, i=-1):
        i = min(i, max(0, s.size - 1))
        if i < 0:
            i = s.size - 1
        s.size -= 1
        s.e = s.e[:i] + s.e[i+1:]

    def clear(s):
        s.e.clear()
        s.size = 0


class DanTable:
    def __init__(s, _size=32):
        s.size = _size
        s.e = [None] * s.size

    def get(s, k):
        k = s.getHash(k)
        if k < s.size and k > -1:
            return s.e[k]
        return None

    def getHash(s, obj):
        return hash(obj) % s.size

    def set(s, elk, elv):
        elk = s.getHash(elk)
        if elk < s.size and elk > -1:
            s.e[elk] = elv
            return True
        return False

    def remove(s, elk):
        return s.set(elk, None)


f = open(OUT_PATH, "r")
f_str = f.readlines()
f.close()

print("Inicialização de Pilha")
time_avg = []
mem_avg = []
for i in range(TIMES):
    start_time = getTime()
    start_mem = getMem()
    d = DanStack()
    for line in f_str:
        d.add(line)
    d.get(1), d.get(100), d.get(1000), d.get(5000), d.get(d.top-1)
    time_avg.append(getTime() - start_time)
    mem_avg.append(getMem() - start_mem)
print(sum(time_avg)/len(time_avg), " segundos")
print(sum(mem_avg)/len(mem_avg) / 1024, " KB consumidos")
print("-------------------------------")

print("Inicialização de Fila")
time_avg.clear()
mem_avg.clear()
for i in range(TIMES):
    start_time = getTime()
    start_mem = getMem()
    d = DanQueue()
    for line in f_str:
        d.add(line)
    d.get(1), d.get(100), d.get(1000), d.get(5000), d.get(d.size-1)
    time_avg.append(getTime() - start_time)
    mem_avg.append(getMem() - start_mem)
print(sum(time_avg)/len(time_avg), " segundos")
print(sum(mem_avg)/len(mem_avg) / 1024, " KB consumidos")
print("-------------------------------")

print("Inicialização de Hashtable")
time_avg.clear()
mem_avg.clear()
for i in range(TIMES):
    start_time = getTime()
    start_mem = getMem()
    d = DanTable(10012)
    for j in range(len(f_str)):
        d.set(j, f_str[j])
    time_avg.append(getTime() - start_time)
    mem_avg.append(getMem() - start_mem)
print(sum(time_avg)/len(time_avg), " segundos")
print(sum(mem_avg)/len(mem_avg) / 1024, " KB consumidos")
print("-------------------------------")

# Adição

for n in range(1, 5):
    time_avg.clear()
    mem_avg.clear()
    np = pow(10, n)
    for i in range(TIMES):
        d = DanStack()
        start_time = getTime()
        start_mem = getMem()
        for line in f_str[:np]:
            d.add(line)
        time_avg.append(getTime() - start_time)
        mem_avg.append(getMem() - start_mem)
    print(f"Adição em Pilha ({np})")
    print(sum(time_avg)/len(time_avg), " segundos")
    print(sum(mem_avg)/len(mem_avg) / 1024, " KB consumidos")
print("-------------------------------")

for n in range(1, 5):
    time_avg.clear()
    mem_avg.clear()
    np = pow(10, n)
    for i in range(TIMES):
        d = DanQueue()
        start_time = getTime()
        start_mem = getMem()
        for line in f_str[:np]:
            d.add(line)
        time_avg.append(getTime() - start_time)
        mem_avg.append(getMem() - start_mem)
    print(f"Adição em Fila ({np})")
    print(sum(time_avg)/len(time_avg), " segundos")
    print(sum(mem_avg)/len(mem_avg) / 1024, " KB consumidos")
print("-------------------------------")


for n in range(1, 5):
    time_avg.clear()
    mem_avg.clear()
    np = pow(10, n)
    for i in range(TIMES):
        d = DanTable()
        start_time = getTime()
        start_mem = getMem()
        for j in range(np):
            d.set(j, f_str[j])
        time_avg.append(getTime() - start_time)
        mem_avg.append(getMem() - start_mem)
    print(f"Adição em Hashtable ({np})")
    print(sum(time_avg)/len(time_avg), " segundos")
    print(sum(mem_avg)/len(mem_avg) / 1024, " KB consumidos")
print("-------------------------------")

# Remoção

for n in range(1, 5):
    time_avg.clear()
    mem_avg.clear()
    np = pow(4, n)
    for i in range(TIMES):
        d = DanStack()
        for line in f_str:
            d.add(line)
        start_time = getTime()
        start_mem = getMem()
        for r in range(np):
            d.remove(np*2)
        time_avg.append(getTime() - start_time)
        mem_avg.append(getMem() - start_mem)
    print(f"Remoção em Pilha ({np})")
    print(sum(time_avg)/len(time_avg), " segundos")
    print(sum(mem_avg)/len(mem_avg) / 1024, " KB consumidos")
print("-------------------------------")

for n in range(1, 5):
    time_avg.clear()
    mem_avg.clear()
    np = pow(4, n)
    for i in range(TIMES):
        d = DanQueue()
        for line in f_str:
            d.add(line)
        start_time = getTime()
        start_mem = getMem()
        for r in range(np):
            d.remove(np*2)
        time_avg.append(getTime() - start_time)
        mem_avg.append(getMem() - start_mem)
    print(f"Remoção em Fila ({np})")
    print(sum(time_avg)/len(time_avg), " segundos")
    print(sum(mem_avg)/len(mem_avg) / 1024, " KB consumidos")
print("-------------------------------")

for n in range(1, 5):
    time_avg.clear()
    mem_avg.clear()
    np = pow(4, n)
    for i in range(TIMES):
        d = DanTable()
        for j in range(len(f_str)):
            d.set(j, f_str[j])
        start_time = getTime()
        start_mem = getMem()
        for j in range(np):
            d.remove(np*2)
        time_avg.append(getTime() - start_time)
        mem_avg.append(getMem() - start_mem)
    print(f"Remoção em Hashtable ({np})")
    print(sum(time_avg)/len(time_avg), " segundos")
    print(sum(mem_avg)/len(mem_avg) / 1024, " KB consumidos")
print("-------------------------------")
