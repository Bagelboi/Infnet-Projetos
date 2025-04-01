import os
from multiprocessing import Pool, Process, Queue
import time
import concurrent.futures as fut
import random

def getTime():
    return time.perf_counter()

#Grupo 1

class TreeNode:
    def __init__(self, val, node_l=None, node_r=None):
        self.val = val
        self.node_l = node_l
        self.node_r = node_r

class BST:
    def __init__(self, val):
        self.root = TreeNode(val)

    def _insert(self, val, node):
        if val > node.val:
            if node.node_r is None:
                node.node_r = TreeNode(val)
            else:
                self._insert(val, node.node_r)
        else:
            if node.node_l is None:
                node.node_l = TreeNode(val)
            else:
                self._insert(val, node.node_l)

    def insert(self, val):
        if self.root is None:
            self.root = TreeNode(val)
        else:
            self._insert(val, self.root)

    def inOrder(self, node):
        if node is not None:
            self.inOrder(node.node_l)
            print(node.val, end=", ")
            self.inOrder(node.node_r)

    def preOrder(self, node):
        if node is not None:
            print(node.val, end=", ")
            self.preOrder(node.node_l)
            self.preOrder(node.node_r)

    def postOrder(self, node):
        if node is not None:
            self.postOrder(node.node_l)
            self.postOrder(node.node_r)
            print(node.val, end=", ")

    def getMin(self, node):
        while node.node_l is not None:
            node = node.node_l
        return node

    def remove(self, val):
        self.root = self._remove(val, self.root)

    def _remove(self, val, node):
        if node is None:
            return None 

        if val < node.val:
            node.node_l = self._remove(val, node.node_l)
        elif val > node.val:
            node.node_r = self._remove(val, node.node_r)
        else:
            if node.node_l is None and node.node_r is None:
                return None
            elif node.node_l is None:
                return node.node_r
            elif node.node_r is None:
                return node.node_l
            else:
                sucessor = self.getMin(node.node_r)
                node.val = sucessor.val
                node.node_r = self._remove(sucessor.val, node.node_r)

        return node

    def _search(self, node, val):
        if node is None:
            return None
        if val == node.val:
            return node
        elif val < node.val:
            return self._search(node.node_l, val)
        else:
            return self._search(node.node_r, val)
        
    def search(self, val):
        return self._search(self.root, val)

    def valida(self, node):
        if node.node_l is not None:
            if node.node_l.val >= node.val:
                return False
            else:
                self.valida(node.node_l)
        if node.node_r is not None:
            if node.node_r.val <= node.val:
                return False
            else:
                self.valida(node.node_r)
        return True
    
    def _searchParalela(self, val, node):
        with fut.ThreadPoolExecutor() as exc:
            futs = []
            if node.node_l is not None:
                futs.append(exc.submit(self._search, node.node_l, val))
            if node.node_r is not None:
                futs.append(exc.submit(self._search, node.node_r, val))

            for futuro in fut.as_completed(futs):
                result = futuro.result()
                if result is not None:
                    return result
                
            return None

    def searchParalela(self, val):
        return self._searchParalela(val, self.root)

    def _dfs(self, node, val):
        if node is None:
            return None
        if node.val == val:
            return [node.val]
        res = [node.val]
        left_result = self._dfs(node.node_l, val)
        if left_result is not None:
            return res + left_result
        right_result = self._dfs(node.node_r, val)
        if right_result is not None:
            return res + right_result
        return None

    def dfs(self, val):
        return self._dfs(self.root, val)
    
    def dfsParalela(self, val):
        node = self.root
        
        if node.val == val:
            return [node.val]
        
        with fut.ThreadPoolExecutor() as exc:
            futs = []
            if node.node_l is not None:
                futs.append(exc.submit(self._dfs, node.node_l, val))
            if node.node_r is not None:
                futs.append(exc.submit(self._dfs, node.node_r, val))

            for futuro in fut.as_completed(futs):
                result = futuro.result()
                if result is not None:
                    return [node.val] + result
        return None
    
    def _getMax(self, node):
        if node is None:
            return -999999
        l_max = self._getMax(node.node_l)
        r_max = self._getMax(node.node_r)
        return max(node.val, l_max, r_max)

    def max(self):
        return self._getMax(self.root)
    
    def maxParalela(self):
        node = self.root
        with fut.ThreadPoolExecutor() as exc:
            futs = []
            if node.node_l is not None:
                futs.append(exc.submit(self._getMax, node.node_l))
            if node.node_r is not None:
                futs.append(exc.submit(self._getMax, node.node_r))

            results = []
            for futuro in fut.as_completed(futs):
                result = futuro.result()
                if result is not None:
                    results.append(result)
            return max(results)
                
            return None


print("\n1.1")
for i in range(1, 6):
    print("\n,", 2**i, "elementos")
    bst = BST(i)
    start_time = getTime()
    for j in range(1, 2**i+1):
        bst.insert(j)
    print("Inserção", getTime()-start_time, "segundos")
    
    print("In order")
    bst.inOrder(bst.root)
    print("Pre order")
    bst.preOrder(bst.root)
    print("Post order")
    start_time = getTime()
    bst.postOrder(bst.root)
    print("\n", getTime()-start_time, "segundos")

print("\n1.2")
for i in range(1, 7):
    print("\n,", 2**i, "elementos")
    bst = BST(i)
    for j in range(1, 2**i+1):
        bst.insert(j)

    r_el = 2**i//2
    #bst.inOrder(bst.root)
    start_time = getTime()
    print("\nRemovendo", r_el)
    bst.remove(r_el)
    print(getTime()-start_time, "segundos")
    bst.inOrder(bst.root)
    print("\n")


print("\n1.3")
for i in range(1, 9):
    print("\n,", 2**i, "elementos")
    bst = BST(i)
    for j in range(1, 2**i+1):
        bst.insert(j)
    
    start_time = getTime()
    search_node = bst.search(2**i//2)
    print("Procurando elemento", 2**i//2)
    print(search_node.val if search_node else None)
    print(getTime()-start_time, "segundos")

print("\n1.4")
for i in range(1, 7):
    print("\n,", 2**i, "elementos")
    bst = BST(i)
    for j in range(1, 2**i+1):
        bst.insert(j)
    
    print("Arvore válida?")
    start_time = getTime()
    print(bst.valida(bst.root))
    print(getTime()-start_time, "segundos")
    
    if bst.root and bst.root.node_l:
        bst.root.node_l.val = bst.root.val + 1
        print("Arvore invalidada válida?")
        start_time = getTime()
        print(bst.valida(bst.root))
        print(getTime()-start_time, "segundos")

#Grupo 2

#Ex 2.1
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

print("\nSoma Paralela vs Linear")

for i_n in range(4, 8):
    i = 10**i_n+1
    print("\n", i, "elementos")
    print(somaParalela(list(range(1, i))))
    print(somaLinear(list(range(1, i))))

#Ex 2.2
def multLinha(linha, mat1, mat2):
    result_linha = []
    for j in range(len(mat2[0])):
        soma = 0
        for k in range(len(mat2)):
            soma += mat1[linha][k] * mat2[k][j]
        result_linha.append(soma)
    return (linha, result_linha)

def matParalela(mat1, mat2):
    linhas = len(mat1)
    colunas = len(mat2[0])
    start_time = getTime()
    
    chunk_size = max(1, linhas // os.cpu_count())
    
    mat_final = [list(range(colunas)) for _ in range(linhas)]
    
    with fut.ThreadPoolExecutor(max_workers=os.cpu_count()) as exc:
        futures = []
        
        for chunk_start in range(0, linhas, chunk_size):
            chunk_end = min(chunk_start + chunk_size, linhas)
            mat1_chunk = mat1[chunk_start:chunk_end]
            
            future = exc.submit(matLinear, mat1_chunk, mat2)
            futures.append((future, chunk_start, chunk_end))

        for future, chunk_start, chunk_end in futures:
            chunk_result = future.result()

            for i in range(chunk_start, chunk_end):
                mat_final[i] = chunk_result[i - chunk_start]
    
    print(getTime() - start_time, "segundos - Matriz Paralela")
    return mat_final

def matLinear(mat1, mat2):
    linhas = len(mat1)
    colunas = len(mat2[0])

    mat_final = [list(range(colunas)) for _ in range(linhas)]
    
    for i in range(linhas):
        linha, linha_res = multLinha(i, mat1, mat2)
        mat_final[linha] = linha_res
    return mat_final

print("\nMatriz")
for i in range(1, 6):
    print(i*50, "x", i*50)
    mat_test = ([[random.randint(1,10) for _ in range(i*50+1)] for _ in range(i*50+1)], [[random.randint(1,10) for _ in range(i*50+1)] for _ in range(i*50+1)])
    print(matParalela(mat_test[0], mat_test[1]))
    start_time = getTime()
    print(matLinear(mat_test[0], mat_test[1]))
    print(getTime() - start_time, "segundos - Matriz Linear")


#Ex 2.3
def isPrimo(num):
    for i in range(2, num):
        if num % i == 0:
            return False
    return True

def primoLinear(nums):
    num_p = 0
    for i in nums:
        if isPrimo(i):
            num_p += 1
    return num_p

def primoParalelo(lst):
    start = getTime()
    chunk_size = len(lst) // os.cpu_count()
    with fut.ThreadPoolExecutor(max_workers=os.cpu_count()) as exc:
        fut_vec = {exc.submit(primoLinear, lst[chunk_i:chunk_i + chunk_size]): chunk_i for chunk_i in range(0, len(lst), chunk_size)}

        results = []
        for future in fut.as_completed(fut_vec):
            results.append(future.result())
        print(f"Paralelo {len(lst)} - {getTime() - start} segundos")
        return sum(results)

#Ex 2.4
print("\nPrimos")
for i_n in range(1, 5):
    i = 15000*i_n+1
    print("\n", i-1, "primos")
    start = getTime()
    print(primoLinear(list(range(1,i))))
    print(f"Linear {i} - {getTime() - start} segundos")
    print(primoParalelo(list(range(1,i))))


#Grupo 3
print("\nArvore paralela")

#Ex 3.1
for i in range(10, 501, 50):
    arvore_giga = BST(1)
    for j in range(2, i):
        arvore_giga.insert(i)
            
    print("\nBuscando", i//2)
    start_time = getTime()
    arvore_giga.search(i//2)
    print(getTime() - start_time, " segundos - Linear")
    start_time = getTime()
    arvore_giga.searchParalela(i//2)
    print(getTime() - start_time, " segundos - Paralela")

#Ex 3.2
for i in range(10, 501, 50):
    arvore_giga = BST(1)
    for j in range(2, i):
        arvore_giga.insert(i)
    print("\nBuscando (DFS)", i//2)
    start_time = getTime()
    arvore_giga.dfs(i//2)
    print(getTime() - start_time, " segundos - Linear")
    start_time = getTime()
    arvore_giga.dfsParalela(i//2)
    print(getTime() - start_time, " segundos - Paralela")

#Ex 3.3
for i in range(1, 5):
    print("\nValor Maximo", 5**i)
    
    arvore_giga = BST(1)
    for j in range(1, 5**i):
        arvore_giga.insert(j)
        
    start_time = getTime()
    print(arvore_giga.max())
    print(getTime() - start_time, " segundos - Linear")
    start_time = getTime()
    print(arvore_giga.maxParalela())
    print(getTime() - start_time, " segundos - Paralela")

#Grupo 4
import ipaddress as ipmod

def isInPrefix(ip, prefix):
    return ipmod.ip_network(ip, strict=False).prefixlen == prefix

class TrieNode:
    def __init__(self):
        self.filhos = {}  
        self.is_prefix = False 
        self.prefix = None

class IPTrie:
    def __init__(self, ipv6=False):
        self.root = TrieNode()
        self.ipv6 = ipv6

    def _ipBits(self, ip):
            packed = ipmod.ip_address(ip).packed
            return ''.join(f'{byte:08b}' for byte in packed)

    def insert(self, cidr):
        try:
            net = ipmod.ip_network(cidr, strict=False)
        except ValueError:
            return

        binary_ip = self._ipBits(str(net.network_address))

        node = self.root
        for i in range(net.prefixlen):
            bit = binary_ip[i]
            if bit not in node.filhos:
                node.filhos[bit] = TrieNode()
            node = node.filhos[bit]
        node.is_prefix = True
        node.prefix = net.prefixlen

    def search(self, ip):
        try:
            ip_addr = ipmod.ip_address(ip)
        except ValueError:
            return None  

        binary_ip = self._ipBits(ip)

        node = self.root
        prefixo = None
        for bit in binary_ip:
            if bit not in node.filhos:
                break
            node = node.filhos[bit]
            if node.is_prefix:
                prefixo = node.prefix 

        return prefixo

def buscaLinear(prefixos, ip):
    ip_addr = ipmod.ip_address(ip)
    prefixo_match = None
    for prefixo in prefixos:
        try:
            net = ipmod.ip_network(prefixo, strict=False)
            if ip_addr in net:
                if prefixo_match is None or net.prefixlen > ipmod.ip_network(prefixo_match, strict=False).prefixlen:
                    prefixo_match = prefixo
        except ValueError:
            continue
    return prefixo_match

print("Grupo 4")
#Ex 4.1
print("\n192.168.1.1", "em", 24, "?")
print(isInPrefix("192.168.1.1", 24))

#Ex 4.2
print("\nIPV4 Trie")
trie = IPTrie()
trie.insert("192.168.1.0/24")
trie.insert("10.0.0.0/24")

print("Buscando 192.168.1.100")
print(trie.search("192.168.1.100")) 
print("Buscando 10.0.0.1")
print(trie.search("10.0.0.1"))       
print("Buscando 172.16.0.1")
print(trie.search("172.16.0.1"))    

#Ex 4.3
print("\nIPV6 Trie")
trie = IPTrie(ipv6=True)
trie.insert("2001:db8::/32")
trie.insert("2001:0db8:85a3::/48")

print("Buscando 2001:db8::1")
print(trie.search("2001:db8::1")) 
print("Buscando 2001:0db8:85a3::1")
print(trie.search("2001:0db8:85a3::1")) 
print("Buscando 2001:0db8:85a3:0000:0000:8a2e:0370:7334")
print(trie.search("2001:0db8:85a3:0000:0000:8a2e:0370:7334"))
print("Buscando 2001:0db8:85a3::2")
print(trie.search("2001:0db8:85a3::2"))  

#Ex 4.4
def gerarPrefixos(n):
    prefixos = []
    for _ in range(n):
        ip = ".".join(str(random.randint(0, 255)) for _ in range(4))
        prefixos.append(f"{ip}/{random.randint(8, 32)}")
    return prefixos


print("\nLinear vs Trie")
ip= "192.168.1.55"
print(f"Buscando {ip}...")

for i in range(2,6):
    prefixos = gerarPrefixos(10**i+1)
    trie = IPTrie()
    for p in prefixos:
        trie.insert(p)
    print("\n", 10**i, "prefixos")    
    start = getTime()
    resultado_linear = buscaLinear(prefixos, ip)
    print(f"Linear: {resultado_linear} - {getTime() - start} segundos")
    start = getTime()
    resultado_trie = trie.search(ip)
    print(f"Trie: {resultado_trie} - {getTime() - start} segundos")
