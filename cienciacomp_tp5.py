from math import sqrt
import time

def getTime():
    return time.perf_counter()

class GrafoCaminho:
    def __init__(self):
        self.vertices = {}

    def adicionarVertice(self, vertice):
        if vertice not in self.vertices:
            self.vertices[vertice] = {} #Matriz

    def adicionarAresta(self, vertice1, vertice2, distancia):
        if vertice1 in self.vertices and vertice2 in self.vertices:
            self.vertices[vertice1][vertice2] = distancia
            self.vertices[vertice2][vertice1] = distancia
            return True
        return False

    def menorCaminho(self, origem, destino):
        #Dijkstra
        if not (origem in self.vertices and destino in self.vertices):
            return None

        visitados = list(self.vertices.keys())
        dist = dict()
        for v in self.vertices:
            dist[v] = float("inf")
        dist[origem] = 0
        pred = {}

        while visitados:
            menor_vertice = min(visitados, key=lambda v: dist[v])

            if dist[menor_vertice] >= float("inf"):
                return None

            for vizinho, peso in self.vertices[menor_vertice].items():
                peso_n = dist[menor_vertice] + peso
                if peso_n < dist[vizinho]:
                    dist[vizinho] = peso_n
                    pred[vizinho] = menor_vertice

            visitados.remove(menor_vertice)

        if destino not in pred:
            return None
        
        caminho = []
        menor_vertice = destino
        dist_total = str(dist[menor_vertice])
        while menor_vertice in pred:
            caminho.append( str(menor_vertice) + " /" + str(dist[menor_vertice]))
            menor_vertice = pred[menor_vertice]
        caminho.append(origem)
            
        caminho.reverse()

        return caminho, dist_total
    
    def menorGrafo(self, origem):
        #Prim
        visitados = []
        mst = []
        peso_vertice = {v: float("inf") for v in self.vertices}
        peso_vertice[origem] = 0
        anterior = {v: None for v in self.vertices}

        while len(visitados) < len(self.vertices):
            menor_vertice = min((v for v in self.vertices if v not in visitados), key=lambda v: peso_vertice[v])
            
            if peso_vertice[menor_vertice] == float("inf"):
                break

            visitados.append(menor_vertice)

            if anterior[menor_vertice] is not None:
                mst.append((anterior[menor_vertice], menor_vertice, peso_vertice[menor_vertice]))
            
            for vizinho, peso_aresta in self.vertices[menor_vertice].items():
                if vizinho not in visitados and peso_aresta < peso_vertice[vizinho]:
                    peso_vertice[vizinho] = peso_aresta
                    anterior[vizinho] = menor_vertice

        return mst


vert = ["A","B","C","D","E","F"]

ares = [
    [('A', 'B', 3)],
    [('A', 'B', 3), ('B', 'C', 7)],
    [('A', 'B', 3), ('B', 'C', 7), ('C', 'D', 2)],
    [('A', 'B', 3), ('B', 'C', 7), ('C', 'D', 2), ('D', 'E', 5)],
    [('A', 'B', 3), ('B', 'C', 7), ('C', 'D', 2), ('D', 'E', 5), ('E', 'F', 1)],
    [('A', 'B', 3), ('B', 'C', 7), ('C', 'D', 2), ('D', 'E', 5), ('E', 'F', 1), ('A', 'C', 4)],
    [('A', 'B', 3), ('B', 'C', 7), ('C', 'D', 2), ('D', 'E', 5), ('E', 'F', 1), ('A', 'C', 4), ('B', 'D', 6)],
    [('A', 'B', 3), ('B', 'C', 7), ('C', 'D', 2), ('D', 'E', 5), ('E', 'F', 1), ('A', 'C', 4), ('B', 'D', 6), ('C', 'E', 9)],
    [('A', 'B', 3), ('B', 'C', 7), ('C', 'D', 2), ('D', 'E', 5), ('E', 'F', 1), ('A', 'C', 4), ('B', 'D', 6), ('C', 'E', 9), ('D', 'F', 8)],
    [('A', 'B', 3), ('B', 'C', 7), ('C', 'D', 2), ('D', 'E', 5), ('E', 'F', 1), ('A', 'C', 4), ('B', 'D', 6), ('C', 'E', 9), ('D', 'F', 8), ('A', 'D', 10)],
    [('A', 'B', 3), ('B', 'C', 7), ('C', 'D', 2), ('D', 'E', 5), ('E', 'F', 1), ('A', 'C', 4), ('B', 'D', 6), ('C', 'E', 9), ('D', 'F', 8), ('A', 'D', 10), ('B', 'E', 12)],
    [('A', 'B', 3), ('B', 'C', 7), ('C', 'D', 2), ('D', 'E', 5), ('E', 'F', 1), ('A', 'C', 4), ('B', 'D', 6), ('C', 'E', 9), ('D', 'F', 8), ('A', 'D', 10), ('B', 'E', 12), ('C', 'F', 11)],
    [('A', 'B', 3), ('B', 'C', 7), ('C', 'D', 2), ('D', 'E', 5), ('E', 'F', 1), ('A', 'C', 4), ('B', 'D', 6), ('C', 'E', 9), ('D', 'F', 8), ('A', 'D', 10), ('B', 'E', 12), ('C', 'F', 11), ('A', 'E', 15)],
    [('A', 'B', 3), ('B', 'C', 7), ('C', 'D', 2), ('D', 'E', 5), ('E', 'F', 1), ('A', 'C', 4), ('B', 'D', 6), ('C', 'E', 9), ('D', 'F', 8), ('A', 'D', 10), ('B', 'E', 12), ('C', 'F', 11), ('A', 'E', 15), ('B', 'F', 14)],
    [('A', 'B', 3), ('B', 'C', 7), ('C', 'D', 2), ('D', 'E', 5), ('E', 'F', 1), ('A', 'C', 4), ('B', 'D', 6), ('C', 'E', 9), ('D', 'F', 8), ('A', 'D', 10), ('B', 'E', 12), ('C', 'F', 11), ('A', 'E', 15), ('B', 'F', 14), ('A', 'F', 20)]
]

for i in range(2, 7):
    prev_a_i = 0
    
    print("\n", i, "Vertices")

    for a in ares:
        a_i = 0
        grafo = GrafoCaminho()
        for v in vert[:i]:
            grafo.adicionarVertice(v)

        for comb in a:
            if grafo.adicionarAresta(comb[0], comb[1], comb[2]):
                a_i += 1

        if prev_a_i == a_i:
            continue
        
        print("\n", a_i, "arestas")
        prev_a_i = a_i

        start_time = getTime()
        print(grafo.menorGrafo("A"))
        print(getTime() - start_time, "segundos - MST")

        start_time = getTime()
        dest = vert[:i][-1]
        try:
            print(grafo.menorCaminho("A", dest))
            print(getTime() - start_time, "segundos - A ->", dest)
        except:
            continue
        
#Ex 2.1
def knapsackGulosa(items, cap):
    ratios = sorted(items.keys(), key=lambda k: items[k][1] / items[k][0], reverse=True)

    cur_peso = 0
    valor_final = 0
    items_add = []
    
    for item in ratios:
        cur_item = items[item]
        if cur_peso +  cur_item[0] <= cap:
            cur_peso += cur_item[0]
            valor_final += cur_item[1]
            items_add.append(item)

    return items_add, cur_peso, valor_final
            
    

items = {
    "item1":(2,40),
    "item2":(3,50),
    "item3":(5,100),
    "item4":(4,90),
    "item5":(3, 40),
    "item6":(2,50)
}

print("\n2.1)")
for i in range(1,6):
    print("\nCapacidade", i*2)
    for j in range(2, 6):
        cur_items = dict(list(items.items())[:j])
        print(j+1, "items")
        print(knapsackGulosa(cur_items, 2*i))

#Ex 2.2
class Mapa2D:
    def __init__(self):
        self.vertices = {}

    def adicionarVertice(self, vertice, coord):
        if vertice not in self.vertices:
            self.vertices[vertice] = coord
            
    def vizinhoProximo(self, origem):
        nao_visitados = list(self.vertices.keys())
        caminho = []
        cur_vertice = origem
        peso_total = 0

        while nao_visitados:
            if cur_vertice in nao_visitados:
                nao_visitados.remove(cur_vertice)
            caminho.append(cur_vertice)

            menor_dist = float("inf")
            prox_vertice = None
            cur_coord = self.vertices[cur_vertice]

            for vizinho in nao_visitados:
                coord = self.vertices[vizinho]
                dist = sqrt((cur_coord[0] - coord[0])**2 + (cur_coord[1] - coord[1])**2)
                if dist < menor_dist:
                    menor_dist = dist
                    prox_vertice = vizinho

            if menor_dist != float("inf"):
                peso_total += menor_dist

            if prox_vertice is None:
                break

            cur_vertice = prox_vertice

        if caminho and caminho[-1] != origem:
            coord_final = self.vertices[caminho[-1]]
            coord_origem = self.vertices[origem]
            dist_volta = sqrt((coord_final[0] - coord_origem[0])**2 + (coord_final[1] - coord_origem[1])**2)
            peso_total += dist_volta
            caminho.append(origem)

        return caminho, peso_total

vertices = {
    "A": (0, 0),
    "B": (1, 5),
    "C": (5, 2),
    "D": (6, 6),
    "E": (8, 3),
    "F":(2,4)
}

print("\n2.2)")
for i in range(2, 7):
    mapa = Mapa2D()
    vert_i = dict(list(vertices.items())[:i])
    
    for v, c in vert_i.items():
        mapa.adicionarVertice(v, c)
    
    print(f"\n{i} vertices")
    for v in mapa.vertices:
        try:
            print(f"{v} -> ", mapa.vizinhoProximo(v))
        except:
            continue
        
#Ex 3.1
import socket
import ssl

def sslHost(host, port):
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        s.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        s.bind((host, port))
        s.listen(5)
        print(f"Iniciando {host}:{port}")
        
        context = ssl.SSLContext(ssl.PROTOCOL_TLS_SERVER)
        context.minimum_version = ssl.TLSVersion.TLSv1_2
        context.load_cert_chain(certfile='server.pem')
        
        with context.wrap_socket(s, server_side=True) as ssock:
            conn, addr = ssock.accept()
            with conn:
                print(f"{addr} conectado")
                conn.sendall(b"Eae cria!\n")
                
                while True:
                    try:
                        data = conn.recv(1024)
                        if not data:
                            break
                        print(f"{addr}: {data.decode()}")
                        
                        send_data = input("Servidor: ")
                        if not send_data:
                            break
                        conn.sendall(send_data.encode())
                    except Exception as e:
                        print(e)
                        break

def sslClient(host, port):
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as s:
        context = ssl.SSLContext(ssl.PROTOCOL_TLS_CLIENT)
        context.check_hostname = False
        context.verify_mode = ssl.CERT_NONE
        
        with context.wrap_socket(s, server_hostname=host) as ssock:
            ssock.connect((host, port))
            print(f"Conectado {host}:{port}")
            
            data = ssock.recv(1024)
            if data:
                print(f"Servidor: {data.decode()}")
            
            while True:
                try:
                    send_data = input("Você: ")
                    if not send_data:
                        break
                    ssock.sendall(send_data.encode())
                    
                    data = ssock.recv(1024)
                    if not data:
                        break
                    print(f"Servidor: {data.decode()}")
                except Exception as e:
                    print(e)
                    break


#Ex 3.2
from scapy.all import *
import sys

def handlePacote(pkt):
    loc_host = "localhost"
    if IP in pkt and pkt[IP].src == loc_host and pkt[IP].dst == loc_host:
        print(pkt.summary())

def ex32():
    interface = input("interface: ")
    sniff(iface=interface, filter=f"dst host localhost and src host localhost", 
          prn=handlePacote, 
          timeout=5)

#Ex 4.1
import ipaddress
    
def arp_scan(network):
    try:
        rede = ipaddress.ip_network(network, strict=False)
    except:
        print("IP invalido!")
        return {}
    
    arp = ARP(pdst=str(rede))
    arp_broad = Ether(dst="ff:ff:ff:ff:ff:ff")/arp
    
    res, res_n = srp(arp_broad, timeout=3)
    
    hosts = {}
    
    for recebido in res_n:
        k = recebido.psrc + "|" + recebido.hwsrc
        #hosts[recebido.psrc] = recebido.hwsrc
        hosts[k] = "Inativo"
    for enviado, recebido in res:
        k = recebido.psrc + "|" + recebido.hwsrc
        hosts[k] = "Ativo"

    return hosts

def ex41():
    hosts = arp_scan(input("Rede: "))
    print(len(hosts), "hosts")
    for ip, mac in hosts.items():
        print(ip, " - ", mac)

if __name__ == "__main__":
    ex41()
    
#Ex 4.2
 
def getMac(ip_address):
    broadcast = Ether(dst="ff:ff:ff:ff:ff:ff")
    arp_request = ARP(pdst=ip_address)
    arp_request_broadcast = broadcast / arp_request
    answered_list = srp(arp_request_broadcast, timeout=1, verbose=False)[0]
    
    if not answered_list:
        return None
    
    return answered_list[0][1].hwsrc


def processPacket(packet):
    if packet.haslayer(ARP) and packet[ARP].op == 2:
        originalmac = getMac(packet[ARP].psrc)
        if originalmac is None:
            return
        responsemac = packet[ARP].hwsrc 
        if originalmac != responsemac:
            print("Sua tabela MAC esta sendo atacada!")


def ex42():
    interface = input("interface: ")
    sniff(iface=interface, store=False, prn=processPacket)

#Ex 5.1
import dns.resolver

def ex51():
    domain = input("Escreva o dominio que quer analisar: ")
    try:
        for record in ['A', 'AAAA', 'NS', 'SOA', 'MX', 'TXT', 'CNAME', 'PTR']:
            try:
                res = dns.resolver.resolve(domain, record)
                print("\nRecord ", record)
                for r in res:
                    print(r)
            except Exception as e:
                print(e)
    except Exception as e:
        print(e)


#Ex 5.2
import subprocess

def ex52():
    port_s = int(input("Porta inicial: "))
    port_f = int(input("Porta final: "))
    p = subprocess.Popen(["nmap", "-sV", "--script", "vulners", input("Digite o host: "), "-p" + ",".join( [str(p) for p in list(range(port_s, port_f+1))] ) ], stdout=subprocess.PIPE)
    (output, err) = p.communicate()
    output = output.decode('utf-8').strip()
    print(output)

