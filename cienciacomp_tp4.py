import random
import time

def getTime():
    return time.perf_counter()

#Grupo 1
print("\nGrupo 1")
class MinHeap:
    def __init__(self):
        self.a = []

    def insert(self, val):
        self.a.append(val)
        i = len(self.a) - 1
        while i > 0 and self.a[(i - 1) // 2] > self.a[i]:
            self.a[i], self.a[(i - 1) // 2] = self.a[(i - 1) // 2], self.a[i]
            i = (i - 1) // 2

    def insertList(self, lst):
        for i in lst:
                    self.insert(i)

    def delete(self, value):
        i = -1
        for j in range(len(self.a)):
            if self.a[j] == value:
                i = j
                break
        if i == -1:
            return
        self.a[i] = self.a[-1]
        self.a.pop()
        self.minHeapify(i, len(self.a))

    def minHeapify(self, i, n):
        smallest = i
        node_l = 2 * i + 1
        node_r = 2 * i + 2
        if node_l < n and self.a[node_l] < self.a[smallest]:
            smallest = node_l
        if node_r < n and self.a[node_r] < self.a[smallest]:
            smallest = node_r
        if smallest != i:
            self.a[i], self.a[smallest] = self.a[smallest], self.a[i]
            self.minHeapify(smallest, n)

    def search(self, val):
        for i in self.a:
            if i == val:
                return True
        return False
                
    def __str__(self):
        return str(self.a)

for i in range(1, 9):
    i_n = 2**i
    print("\n", i_n, "elementos")
    heap = MinHeap()
    i_list = list(range(1, i_n+1))
    random.shuffle(i_list)
    
    start_time = getTime()
    heap.insertList(i_list)
    print(getTime() - start_time, " Segundos - Inserindo lista remexida")
    print(heap)
    
    start_time = getTime()    
    heap.insert(0)
    print(getTime() - start_time, " Segundos - Inserindo 0")
    
    print(heap)
    start_time = getTime()    
    print(heap.search(i_n//2))
    print(getTime() - start_time, " Segundos - Procurando", i_n//2)
    
    start_time = getTime()    
    heap.delete(i_n//2)
    print(getTime() - start_time, " Segundos - Apagando", i_n//2)
    print(heap)

#Grupo 2
print("\nGrupo 2")
class TrieNode:
    def __init__(self):
        self.arr = {}
        self.final = False

class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):
        node = self.root
        for char in word:
            if char not in node.arr:
                node.arr[char] = TrieNode()
            node = node.arr[char]
        node.final = True

    def search(self, word):
        node = self.root
        for char in word:
            if char not in node.arr:
                return False
            node = node.arr[char]
        return True
    
            
    def autoComplete(self, prefixo):
        lst = []
        
        def wordRec(node, word):
            if node.final:
                lst.append( word )
     
            for k, nodes in node.arr.items():
                wordRec(nodes, word + k)
                
        if not self.search(prefixo):
            return lst

        node = self.root
        for char in prefixo:
            node = node.arr[char]
            
        wordRec(node, prefixo)
        
        return lst

    def delete(self, word):
        if not self.search(word):
            return False
        return self._delete(self.root, word, 0)
    
    def _delete(self, node, word, depth):
        if depth == len(word):
            if not node.final:
                return False
            node.final = False
            return len(node.arr) == 0

        char = word[depth]
        if char not in node.arr:
            return False

        apagar_filho = self._delete(node.arr[char], word, depth + 1)

        if apagar_filho:
            del node.arr[char]
            return len(node.arr) == 0 and not node.final

        return False


for i in range(1, 7):
    i_n = 2**i
    print("\n\n", i_n, " palavras")
    
    words = [
        "casa", "casamento", "casulo", "cachorro", "casaco", "casinha", "casbah", "casca",
        "cadeira", "caderno", "cadeado", "café", "caixa", "caju", "cama", "caminho",
        "camisa", "caneta", "caneca", "canção", "canguru", "cantar", "capaz", "capitão",
        "carro", "cartão", "casa", "castelo", "cavalo", "caverna", "cabelo", "cabide",
        "cacto", "cadáver", "cafofo", "caipira", "calça", "caldo", "calma", "cama"
    ][:i_n]

    trie = Trie()
    start_time_total = getTime()
    for word in words:
        trie.insert(word)
    print(getTime() - start_time_total, "Segundos - Inserindo (total)") 
    
    search_word = ""
    for word in sorted(words, key=lambda w: len(w)):
        if len(word) == 3 + i:
            search_word = word
            break
    start_time = getTime()
    found = trie.search(search_word)
    print("\n", getTime() - start_time, "Segundos - Buscando", search_word, "Achada?:", found)
    

    start_time = getTime()
    auto_word = "c"
    print(trie.autoComplete(auto_word))
    print("\n", getTime() - start_time, "Segundos - Autocomplete para", auto_word)

    start_time = getTime()    
    for word in sorted(words, key=lambda w: len(w)):
        if len(word) == 3 + i:
            delete_word = word
            break
        
    trie.delete(delete_word)
    print("\n", getTime() - start_time, "Segundos - Apagando", delete_word)
        
#Grupo 3
print("\nGrupo 3")
class Grafo:
    def __init__(self):
        self.lista_adjacencia = {} 

    def adicionarVertice(self, vertice):
        if vertice not in self.lista_adjacencia:
            self.lista_adjacencia[vertice] = []

    def adicionarAresta(self, vertice1, vertice2):
        if vertice1 != vertice2 and vertice1 in self.lista_adjacencia and vertice2 in self.lista_adjacencia:
            self.lista_adjacencia[vertice1].append(vertice2)
            return True
        return False

    def bfs(self, vertice):
        fila = [vertice]
        visitados = {vertice: None}
        ordem = []
        
        while fila:
            node = fila.pop(0)

            for vizinho in self.lista_adjacencia[node]:
                if vizinho not in visitados:
                    fila.append(vizinho)
                    visitados[vizinho] = node
            ordem.append(node)

        return ordem
    
    def dfs(self, vertice):
        visitados = []
        ordem = []

        def subDfs(vertice):
            visitados.append(vertice)
            ordem.append(vertice)
            for vizinho in self.lista_adjacencia[vertice]:
                if vizinho not in visitados:
                    subDfs(vizinho)

        subDfs(vertice)
        return ordem

    def caminho(self, origem, destino):
        fila = [origem]
        visitados = {origem: None}

        while fila:
            node = fila.pop(0)

            if node == destino:
                break

            for vizinho in self.lista_adjacencia[node]:
                if vizinho not in visitados:
                    fila.append(vizinho)
                    visitados[vizinho] = node

        caminho = []
        node = destino
        while node is not None:
            caminho.append(node)
            node = visitados[node]
        caminho.reverse()

        return caminho
        


vert = ["A","B","C","D","E","F"]

ares = [
    [('A', 'B')],  
    [('A', 'B'), ('B', 'C')],  
    [('A', 'B'), ('B', 'C'), ('C', 'D')],  
    [('A', 'B'), ('B', 'C'), ('C', 'D'), ('D', 'E')],  
    [('A', 'B'), ('B', 'C'), ('C', 'D'), ('D', 'E'), ('E', 'F')], 
    [('A', 'B'), ('B', 'C'), ('C', 'D'), ('D', 'E'), ('E', 'F'), ('A', 'C')],  
    [('A', 'B'), ('B', 'C'), ('C', 'D'), ('D', 'E'), ('E', 'F'), ('A', 'C'), ('B', 'D')],  
    [('A', 'B'), ('B', 'C'), ('C', 'D'), ('D', 'E'), ('E', 'F'), ('A', 'C'), ('B', 'D'), ('C', 'E')],  
    [('A', 'B'), ('B', 'C'), ('C', 'D'), ('D', 'E'), ('E', 'F'), ('A', 'C'), ('B', 'D'), ('C', 'E'), ('D', 'F')],  
    [('A', 'B'), ('B', 'C'), ('C', 'D'), ('D', 'E'), ('E', 'F'), ('A', 'C'), ('B', 'D'), ('C', 'E'), ('D', 'F'), ('A', 'D')],  
    [('A', 'B'), ('B', 'C'), ('C', 'D'), ('D', 'E'), ('E', 'F'), ('A', 'C'), ('B', 'D'), ('C', 'E'), ('D', 'F'), ('A', 'D'), ('B', 'E')],  
    [('A', 'B'), ('B', 'C'), ('C', 'D'), ('D', 'E'), ('E', 'F'), ('A', 'C'), ('B', 'D'), ('C', 'E'), ('D', 'F'), ('A', 'D'), ('B', 'E'), ('C', 'F')],  
    [('A', 'B'), ('B', 'C'), ('C', 'D'), ('D', 'E'), ('E', 'F'), ('A', 'C'), ('B', 'D'), ('C', 'E'), ('D', 'F'), ('A', 'D'), ('B', 'E'), ('C', 'F'), ('A', 'E')],  
    [('A', 'B'), ('B', 'C'), ('C', 'D'), ('D', 'E'), ('E', 'F'), ('A', 'C'), ('B', 'D'), ('C', 'E'), ('D', 'F'), ('A', 'D'), ('B', 'E'), ('C', 'F'), ('A', 'E'), ('B', 'F')],  
    [('A', 'B'), ('B', 'C'), ('C', 'D'), ('D', 'E'), ('E', 'F'), ('A', 'C'), ('B', 'D'), ('C', 'E'), ('D', 'F'), ('A', 'D'), ('B', 'E'), ('C', 'F'), ('A', 'E'), ('B', 'F'), ('A', 'F')]
]


for i in range(2, 6):
    prev_a_i = 0
    
    print("\n", i, "Vertices")

    for a in ares:
        a_i = 0
        grafo = Grafo()
        for v in vert[:i]:
            grafo.adicionarVertice(v)

        for comb in a:
            if grafo.adicionarAresta(comb[0], comb[1]):
                a_i += 1

        if prev_a_i == a_i:
            continue
        
        print("\n", a_i, "arestas")
        prev_a_i = a_i

        start_time = getTime()
        print(grafo.dfs("A"))
        print(getTime() - start_time, "segundos - DFS")
        
        start_time = getTime()    
        print(grafo.bfs("A"))
        print(getTime() - start_time, "segundos - BFS")
        
        start_time = getTime()
        dest = vert[:i][-1]
        try:
            print(grafo.caminho("A", dest))
            print(getTime() - start_time, "segundos - A ->", dest)
        except:
            continue

#Grupo 4
import socket

def tcpHost(host, port):
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.bind((host, port))
        s.listen()
        print(f"Iniciado")
        conn, addr = s.accept()
        with conn:
            print(f"{addr} conectado")
            conn.sendall(b"Ola!\n")
            while True:
                data = conn.recv(512)
                if not data:
                    break
                print(f"{addr}: {data.decode()}")
                send_data = input("Você: ") 
                if len(send_data ) < 1:
                    break
                else:
                    conn.sendall(send_data.encode())

def tcpClient(host, port):
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.connect((host, port))
        print(f"Conectado ao {host}:{port}")
        while True:
            data = s.recv(512)
            if not data:
                break
            data = data.decode()
            if len(data) > 1:
                print(f"Servidor: {data}")
            send_data = input("Você: ")
            if len(send_data ) < 1:
                break
            else:
                s.sendall(send_data.encode())


def udpHost(host, port):
    with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as s:
        s.bind((host, port))
        print(f"Iniciado")
        while True:
            data, addr = s.recvfrom(512)
            print(f"{addr}: {data.decode()}")
            s.sendto(f"{addr} - ACK!".encode(), addr)

def udpClient(host, port):
    with socket.socket(socket.AF_INET, socket.SOCK_DGRAM) as s:
        while True:
            send_data = input("Você: ")
            if len(send_data) > 0: 
                s.sendto(send_data.encode(), (host, port))  
            data, addr = s.recvfrom(512)  
            print(data.decode())

