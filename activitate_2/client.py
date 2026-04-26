import socket

SERVER_HOST = '127.0.0.1'
SERVER_PORT = 9999
BUFFER_SIZE = 1024
TIMEOUT     = 5

client_socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
client_socket.settimeout(TIMEOUT)

este_conectat = False

def trimite_comanda(mesaj: str) -> str:
    """Functie auxiliara pentru comunicarea cu serverul UDP."""
    try:
        client_socket.sendto(mesaj.encode('utf-8'), (SERVER_HOST, SERVER_PORT))
        date_brute, _ = client_socket.recvfrom(BUFFER_SIZE)
        return date_brute.decode('utf-8')
    except socket.timeout:
        return "EROARE: Serverul nu raspunde (timeout depasit)."
    except Exception as e:
        return f"EROARE DE SISTEM: {e}"

def afisare_meniu():
    """Afiseaza optiunile disponibile pentru utilizator."""
    print("\n" + "=" * 55)
    print("  CLIENT UDP - INTERFATA DE GESTIONARE")
    print("=" * 55)
    print("  Status: " + ("CONECTAT" if este_conectat else "DECONECTAT"))
    print("-" * 55)
    print("  1. CONNECT              - Stabileste legatura")
    print("  2. DISCONNECT           - Inchide sesiunea")
    print("  3. PUBLISH <mesaj>      - Adauga un mesaj nou")
    print("  4. DELETE <id>          - Elimina un mesaj propriu")
    print("  5. LIST                 - Vezi toate mesajele")
    print("  6. EXIT                 - Termina executia")
    print("=" * 55)

afisare_meniu()

while True:
    try:
        intrare = input("\nCOMANDA >> ").strip()
    except (KeyboardInterrupt, EOFError):
        print("\nIntererupere detectata. Inchidere...")
        break

    if not intrare:
        continue

    parti = intrare.split(' ', 1)
    comanda = parti[0].upper()

    if comanda == 'EXIT':
        print("Se inchide aplicatia client...")
        break

    if comanda in ['PUBLISH', 'DELETE', 'LIST', 'DISCONNECT'] and not este_conectat:
        print("ATENTIE: Trebuie sa executati comanda CONNECT inainte de orice alta actiune.")
        continue

    if comanda == 'PUBLISH':
        if len(parti) < 2 or parti[1].strip() == "":
            print("EROARE LOCALA: Sintaxa corecta este: PUBLISH <text_mesaj>")
            continue

    if comanda == 'DELETE':
        if len(parti) < 2:
            print("EROARE LOCALA: Sintaxa corecta este: DELETE <id_numeric>")
            continue
        if not parti[1].strip().isdigit():
            print("EROARE LOCALA: ID-ul furnizat trebuie sa fie un numar.")
            continue

    raspuns = trimite_comanda(intrare)
    print(f"\n[SERVER]: {raspuns}")

    if comanda == 'CONNECT' and raspuns.startswith("OK"):
        este_conectat = True
    elif comanda == 'DISCONNECT' and raspuns.startswith("OK"):
        este_conectat = False

client_socket.close()
print("Sesiune terminata. Socket eliberat.")

