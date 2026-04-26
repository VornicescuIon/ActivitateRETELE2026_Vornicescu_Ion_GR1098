import socket

HOST        = '127.0.0.1'
PORT        = 9999
BUFFER_SIZE = 1024

clienti_conectati = {}
mesaje = {}
id_curent = 1

server_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
server_socket.bind((HOST, PORT))

print("=" * 50)
print(f"  SERVER UDP pornit pe {HOST}:{PORT}")
print("  Asteptam mesaje de la clienti...")
print("=" * 50)

while True:
    try:
        date_brute, adresa_client = server_socket.recvfrom(BUFFER_SIZE)
        mesaj_primit = date_brute.decode('utf-8').strip()

        parti = mesaj_primit.split(' ', 1)
        comanda = parti[0].upper()
        argumente = parti[1] if len(parti) > 1 else ''

        print(f"\n[INFO] Cerere de la {adresa_client}: '{comanda}'")

        if comanda == 'CONNECT':
            if adresa_client in clienti_conectati:
                raspuns = "EROARE: Conexiunea este deja activa."
            else:
                clienti_conectati[adresa_client] = True
                raspuns = f"OK: Te-ai conectat. Clienti activi: {len(clienti_conectati)}"
                print(f"[LOG] Client nou: {adresa_client}")

        elif comanda == 'DISCONNECT':
            if adresa_client in clienti_conectati:
                del clienti_conectati[adresa_client]
                raspuns = "OK: Deconectare reusita."
                print(f"[LOG] Client deconectat: {adresa_client}")
            else:
                raspuns = "EROARE: Sesiunea nu este activa."

        elif comanda == 'PUBLISH':
            if adresa_client not in clienti_conectati:
                raspuns = "EROARE: Trebuie sa fii conectat (CONNECT) pentru a publica."
            elif not argumente:
                raspuns = "EROARE: Mesajul nu are continut."
            else:
                mesaje[id_curent] = {'text': argumente, 'autor': adresa_client}
                raspuns = f"OK: Mesaj salvat cu ID-ul {id_curent}"
                id_curent += 1

        elif comanda == 'DELETE':
            if adresa_client not in clienti_conectati:
                raspuns = "EROARE: Functia de stergere necesita autentificare."
            elif not argumente.isdigit():
                raspuns = "EROARE: Specifica un ID valid (numeric)."
            else:
                id_sters = int(argumente)
                if id_sters not in mesaje:
                    raspuns = f"EROARE: ID-ul {id_sters} nu exista."
                elif mesaje[id_sters]['autor'] != adresa_client:
                    raspuns = "EROARE: Nu poti sterge mesajul altui utilizator."
                else:
                    mesaje.pop(id_sters)
                    raspuns = f"OK: Mesajul {id_sters} a fost eliminat."

        elif comanda == 'LIST':
            if adresa_client not in clienti_conectati:
                raspuns = "EROARE: Conecteaza-te pentru a vedea lista."
            elif not mesaje:
                raspuns = "OK: Serverul nu contine mesaje in acest moment."
            else:
                lista_formatata = [f"[ID {k}] {v['text']}" for k, v in mesaje.items()]
                raspuns = "OK: Lista mesaje:\n" + "\n".join(lista_formatata)

        else:
            raspuns = f"EROARE: Comanda '{comanda}' nu este recunoscuta."

        server_socket.sendto(raspuns.encode('utf-8'), adresa_client)
        print(f"[INFO] Raspuns trimis catre {adresa_client}")

    except KeyboardInterrupt:
        print("\n[SERVER] Oprire...")
        break
    except Exception as e:
        print(f"[EROARE] {e}")

server_socket.close()