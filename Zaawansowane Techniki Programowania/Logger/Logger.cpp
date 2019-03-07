#include <iostream>
#include <fstream>
#include <ctime>
#include <sstream>
#include <winsock2.h>

using namespace std;

class Record
{
	public:
		string Time;
		int Priority;
		int ID;
		string Header;
		string Message;
		string IP;
		
		string get_time()
		{
			return this->Time;
		}
		
		int get_priority()
		{
			return this->Priority;
		}
		
		int get_ID()
		{
			return this->ID;
		}
		
		string get_message()
		{
			return this->Message;
		}
		
		string get_header()
		{
			return this->Header;
		}
		
		string get_IP()
		{
			return this->IP;
		}
		
		void set_time()
		{
			time_t now = time(0);
			string dt = ctime(&now);
			this->Time = dt;
		}
		
		void set_priority(int priority)
		{
			this->Priority = priority;
		}
		
		void set_ID(int ID)
		{
			this->ID = ID;
		}
		
		void set_message(string Message)
		{
			this->Message = Message;
		}
		
		void set_header(string Header)
		{
			this->Header = Header;
		}
		
		void set_IP(string IP)
		{
			this->IP = IP;
		}
		
		Record(int Priority, int ID, string Message)
		{
			set_time();
			this->Priority = Priority;
			this->ID = ID;
			this->Message = Message;
		}
};




class Formatter
{
	virtual string format(Record& record) = 0;
};

// [ precondition: record.Time is not null ]
// [ precondition: record.priority is not null ]
// [ precondition: record.ID is not null ]
// [ precondition: record.Message is not null ]
class TXT_Formatter : public Formatter
{
public:
    virtual string format(Record& record)
    {
        stringstream ss;
        ss << "Time: " << record.get_time() << " Priority: " << record.get_priority() << " ID: " << record.get_ID() << " Message: " << record.get_message() << endl;
        return ss.str();
        // [ postcondition: ss.str() is not null ]
    } 
};

// [ precondition: record.Time is not null ]
// [ precondition: record.priority is not null ]
// [ precondition: record.ID is not null ]
// [ precondition: record.Message is not null ]
class Console_Formatter : public Formatter
{
public:
    virtual string format(Record& record)
    {
        stringstream ss;
        ss << "Time: " << record.get_time() << endl << " Priority: " << record.get_priority() << endl << " ID: " << record.get_ID() << endl << " Message: " << record.get_message() << endl << endl;
        return ss.str();
        // [ postcondition: ss.str() is not null ]
    } 
};

// [ precondition: record.Time is not null ]
// [ precondition: record.priority is not null ]
// [ precondition: record.ID is not null ]
// [ precondition: record.Message is not null ]
class CSV_Formatter : public Formatter
{
	public:
		virtual string format(Record& record)
		{
			stringstream ss;
			ss << record.get_time() << ";" << record.get_priority() << ";" << record.get_ID() << ";" << record.get_message() << ";" << endl;
			return ss.str();
			// [ postcondition: ss.str() is not null ]
		}
};

// [ precondition: record.priority is not null ]
// [ precondition: record.IP is not null ]
// [ precondition: record.Message is not null ]
// [ precondition: record.Header is not null ]
class Socket_Formatter : public Formatter
{
	public:
		virtual string format(Record& record)
		{
			stringstream ss;
			ss << record.get_priority() << ":" << record.get_IP() << ":" << record.get_message() << ":" << record.get_header() << endl;
			return ss.str();
			// [ postcondition: ss.str() is not null ]
		}
};





class Appender //Outputter
{
    virtual void Append(Record& record, string level) = 0;
};

class TXT_Appender : public Appender //Txt outputter
{
	public:
		string filename;
	    void Append(Record& record, string level)
		{
			filename = "file_log.txt";
			TXT_Formatter f_txt;
			
			cout << "Start logging to TXT file" << endl;
	        ofstream output_file;
	        output_file.open (filename, std::fstream::app);
	        output_file << level << f_txt.format(record);
	        output_file.close();	
			// [ postcondition: filename.size() > 0 ]
			// [ postcondition: output_file is closed ]		
	    }
};

class CSV_Appender : public Appender //Csv outputter
{
	public:
		string filename;
	    void Append(Record& record, string level)
		{
			filename = "file_log.csv";
			CSV_Formatter f_csv;
			
			cout << "Start logging to CSV file" << endl;
	        ofstream output_file;
	        output_file.open (filename, std::fstream::app);
	        output_file << level << f_csv.format(record);
	        output_file.close();	
			// [ postcondition: filename.size() > 0 ]
			// [ postcondition: output_file is closed ]			
	    }
};


class Socket_Appender : public Appender //Socket outputter
{
	public:
		void Append(Record& record, string level)
		{
	        cout<<"Log to socket"<<endl;
	        WSADATA WSAData;
	        SOCKET server;
	        SOCKADDR_IN addr;
	
	        WSAStartup(MAKEWORD(2,0), &WSAData);
	        server = socket(AF_INET, SOCK_STREAM, 0);
	
	        addr.sin_addr.s_addr = inet_addr("127.0.0.1");
	        
	        addr.sin_family = AF_INET;
	        addr.sin_port = htons(27015);
	
	        connect(server, (SOCKADDR *)&addr, sizeof(addr));
	        cout<<"Connected to server!"<<endl;
	
	        char buffer[1024];
	        Socket_Formatter f_sock;
	        record.set_IP("128.64.28.13.5");
	        record.set_header("MyHeader");
	        
	        string str_buffer = f_sock.format(record);
	        strcpy(buffer, str_buffer.c_str()); 
	        
	        send(server, buffer, sizeof(buffer), 0);
	        cout<<"Message sent!"<<endl;
	
	        closesocket(server);
	        WSACleanup();
	        cout<<"Socket closed."<<endl<<endl;
	        // [ postcondition: server socket is closed ]
	    }
};

class Console_Appender : public Appender //Console Outputter
{
	public:
	    void Append(Record& record, string level)
		{
	        cout << "Start logging to console..." << endl;
	        Console_Formatter c;
	        cout << level << c.format(record) << endl;
	    }
};







// [ precondition: record.priority is not null ]
// [ precondition: record.priority > 0 ]
class Logger
{
	public:
		string info() { string str = "## INFO ## "; return str; } //level 1
		string debug() { string str = "## DEBUG ## "; return str; } //level 2
		string warning() { string str = "## WARNING ## "; return str; } //level 3
		string alert() { string str = "## ALERT ## "; return str; } //level 4
		string default_message() { string str = "## DEFAULT ## "; return str; } //other level
		
		int level;
		string custom_IP;
		
		Logger(int level)
		{
			this->level = level;
		}
		
		void set_level(int level)
		{
			this->level = level;
		}
		
		void set_custom_IP(string custom_IP)
		{
			this->custom_IP = custom_IP;
		}
		
		int get_level()
		{
			return level;
		}
	
	
		void log(Record& record, int output_type)
		{
			string level_str;
			
			if (record.Priority == 1 and output_type == 4)
				level_str = debug();
			else if (record.Priority == 4 and output_type == 4 and record.Message.size() > 10)
				level_str = alert();
			else if (output_type == 4)
			{
				cout << "No socket logging!" << endl; 
				return;
			}
			if (record.Priority < level)
			{
				cout << "No logging!" << endl; 
				return;
			}
			else if (record.Priority == 1)
				level_str = info();
			else if (record.Priority == 2)
				level_str = debug();
			else if (record.Priority == 3)
				level_str = warning();
			else if (record.Priority == 4)
				level_str = alert();
			else 
				level_str = default_message();
			
			
		    switch (output_type)
			{
		    	case 1: //TXT
		    	{
					TXT_Appender fileOut;
		        	fileOut.Append(record, level_str);
		        	break;
		    	}
		    	
		    	case 2: //CSV
		    	{
					CSV_Appender fileOut;
		        	fileOut.Append(record, level_str);
		        	break;
		    	}
		        
		        
			    case 3: //CONSOLE
			    {
			    	Console_Appender consoleOut;
			        consoleOut.Append(record, level_str);
			        break;
			 	}
			 	
			 	case 4:
		 		{
		 			Socket_Appender socketOut;
		 			socketOut.Append(record, level_str);
		 			break;
				}
			        
			    default:
			    	cout << "Wrong output type!" << endl;
			        break;
		    }
		}
};




int main()
{
	Record r = Record(3, 34, "aaa");
	Logger l = Logger(2);
	
	l.log(r, 1);
	l.log(r, 2);
	l.log(r, 3);
	
	r = Record(1, 55, "bbb");
	l.log(r, 1);
	l.log(r, 2);
	l.log(r, 3);
	
	l = Logger(0);
	l.log(r, 1);
	l.log(r, 2);
	l.log(r, 3);
	
	l.log(r, 4); //socket
	
	r = Record(4, 55, "bbb");
	l.log(r, 4); 
	
	
	r = Record(4, 55, "bbbbbbbbbbbbbbbbbbbbbbb");
	l.log(r, 4); 

    return 0;
}



