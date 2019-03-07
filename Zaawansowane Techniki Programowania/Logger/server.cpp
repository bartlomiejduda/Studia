#include <iostream>
#include <winsock2.h>



using namespace std;

int main()
{
    WSADATA WSAData;

    SOCKET server, client, management;

    SOCKADDR_IN serverAddr, clientAddr, managementAddr;

    WSAStartup(MAKEWORD(2,0), &WSAData);
    server = socket(AF_INET, SOCK_STREAM, 0);

    serverAddr.sin_addr.s_addr = INADDR_ANY;
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(27015);

    bind(server, (SOCKADDR *)&serverAddr, sizeof(serverAddr));
    listen(server, 0);

    cout << "Listening for incoming connections..." << endl;

    char buffer[1024];
    int clientAddrSize = sizeof(clientAddr);

	while(true)
	{
		if((client = accept(server, (SOCKADDR *)&clientAddr, &clientAddrSize)) != INVALID_SOCKET)
		{
	        cout << "Client connected!" << endl;
	        recv(client, buffer, sizeof(buffer), 0);
	        cout << "Client's log': " << buffer << endl;
	        memset(buffer, 0, sizeof(buffer));
	
	        closesocket(client);
	        cout << "Client disctonnected." << endl;
	        //getchar();
	    }
		
		
	}
    
}
