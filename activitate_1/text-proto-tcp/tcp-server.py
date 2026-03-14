import socket
import threading

HOST = "127.0.0.1"
PORT = 3333
BUFFER_SIZE = 1024

class State:
    def __init__(self):
        self.data = {}
        self.lock = threading.Lock()

    def add(self, key, value):
        with self.lock:
            self.data[key] = value
        return "OK - record add"

    def get(self, key):
        with self.lock:
            if key in self.data:
                return f"DATA {self.data[key]}"
            return "ERROR invalid key"

    def remove(self, key):
        with self.lock:
            if key in self.data:
                del self.data[key]
                return "OK value deleted"
            return "ERROR invalid key"
            
    def list_all(self):
        with self.lock:
            elemente = [f"{k}={v}" for k, v in self.data.items()]
            return "DATA|" + ",".join(elemente)

    def count_all(self):
        with self.lock:
            return f"DATA {len(self.data)}"

    def clear_all(self):
        with self.lock:
            self.data.clear()
            return "all data deleted"

    def update_record(self, key, new_val):
        with self.lock:
            if key in self.data:
                self.data[key] = new_val
                return "Data updated"
            return "ERROR invalid key"

    def pop_record(self, key):
        with self.lock:
            if key in self.data:
                valoare = self.data.pop(key)
                return f"DATA {valoare}"
            return "ERROR invalid key"

state = State()

def process_command(command):
    parts = command.split()
    if not parts:
        return "ERROR invalid format"

    cmd = parts[0].lower()

    if cmd == "list":
        return state.list_all()
    if cmd == "count":
        return state.count_all()
    if cmd == "clear":
        return state.clear_all()
    if cmd == "quit":
        return "BYE"

    if len(parts) < 2:
        return "ERROR missing key"
    
    key = parts[1]

    if cmd == "add" and len(parts) > 2:
        valoare_completa = " ".join(parts[2:])
        return state.add(key, valoare_completa)
        
    elif cmd == "get":
        return state.get(key)
        
    elif cmd == "remove":
        return state.remove(key)
        
    elif cmd == "update" and len(parts) > 2:
        valoare_noua = " ".join(parts[2:])
        return state.update_record(key, valoare_noua)
        
    elif cmd == "pop":
        return state.pop_record(key)

    return "ERROR unknown command"

def handle_client(client_socket):
    with client_socket:
        while True:
            try:
                data = client_socket.recv(BUFFER_SIZE)
                if not data:
                    break

                received_str = data.decode('utf-8').strip()
                if not received_str:
                    continue

                response = process_command(received_str)
                
                final_payload = f"{len(response)} {response}".encode('utf-8')
                client_socket.sendall(final_payload)

                if response == "BYE":
                    break

            except Exception as e:
                err_msg = f"Error: {str(e)}".encode('utf-8')
                client_socket.sendall(err_msg)
                break

def start_server():
    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as server_socket:
        server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        server_socket.bind((HOST, PORT))
        server_socket.listen(5)
        print(f"[RUNNING] Serverul este activ pe {HOST}:{PORT}")

        while True:
            client_sock, addr = server_socket.accept()
            print(f"[CONNECTION] Client nou: {addr}")
            
            thread = threading.Thread(target=handle_client, args=(client_sock,))
            thread.daemon = True
            thread.start()

if __name__ == "__main__":
    start_server()