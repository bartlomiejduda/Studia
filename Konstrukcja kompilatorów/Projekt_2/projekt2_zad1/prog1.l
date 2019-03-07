%{
#include <stdio.h>
int yylex(void);
int yywrap(void);
int chars = 0, words = 0, lines = 0;
char* strr;

%}

%%

"//".*$ { fprintf(yyout, "<font color=\"grey\">%s</font>", yytext);  }
"/*".*"*/" { fprintf(yyout, "<font color=\"grey\">%s</font>", yytext);  }
[#][Ii][Nn][Cc][Ll][Uu][Dd][Ee] { fprintf(yyout, "<font color=\"red\">%s</font>", yytext); }
[<] { fprintf(yyout, "&#60"); }
[>] { fprintf(yyout, "&#62"); }
[A-Za-z][0-9] { fprintf(yyout, "%s", yytext); } 
[0-9] { fprintf(yyout, "<font color=\"orange\">%s</font>", yytext);  }
"\"".*"\""  { fprintf(yyout, "<font color=\"violet\"><b>%s</b></font>", yytext);  }
"\'".*"\'"  { fprintf(yyout, "<font color=\"violet\"><b>%s</b></font>", yytext);  }
[Ii][Ff] { fprintf(yyout, "<font color=\"blue\">%s</font>", yytext);  }
[Ee][Ll][Ss][Ee] { fprintf(yyout, "<font color=\"blue\">%s</font>", yytext);  } 
[Ff][Oo][Rr] { fprintf(yyout, "<font color=\"blue\">%s</font>", yytext);  }
[Ww][Hh][Ii][Ll][Ee] { fprintf(yyout, "<font color=\"blue\">%s</font>", yytext); }
[A-Za-z|_|-][Dd][Oo]  { fprintf(yyout, "%s", yytext); }
[Dd][Oo] { fprintf(yyout, "<font color=\"blue\">%s</font>", yytext); }
[Tt][Rr][Uu][Ee] { fprintf(yyout, "<font color=\"blue\">%s</font>", yytext);  }
[Ss][Ww][Ii][Tt][Cc][Hh] { fprintf(yyout, "<font color=\"blue\">%s</font>", yytext); }
[Cc][Aa][Ss][Ee] { fprintf(yyout, "<font color=\"blue\">%s</font>", yytext); }
[Bb][Rr][Ee][Aa][Kk] { fprintf(yyout, "<font color=\"blue\">%s</font>", yytext); }
[A-Za-z|_|-][Cc][Ll][Aa][Ss][Ss] { fprintf(yyout, "%s", yytext); }
[Cc][Ll][Aa][Ss][Ss] { fprintf(yyout, "<font color=\"blue\">%s</font>", yytext); }
[Pp][Uu][Bb][Ll][Ii][Cc] { fprintf(yyout, "<font color=\"blue\">%s</font>", yytext); }
[Pp][Rr][Ii][Vv][Aa][Tt][Ee] { fprintf(yyout, "<font color=\"blue\">%s</font>", yytext); }
[Uu][Ss][Ii][Nn][Gg]" "[Nn][Aa][Mm][Ee][Ss][Pp][Aa][Cc][Ee]  { fprintf(yyout, "<font color=\"blue\">%s</font>", yytext); }
[Rr][ee][Tt][Uu][Rr][Nn] { fprintf(yyout, "<font color=\"blue\">%s</font>", yytext);  }
[Dd][Ee][Ff][Ii][Nn][Ee] { fprintf(yyout, "<font color=\"green\">%s</font>", yytext);  }
[A-Za-z|_|-][Ii][Nn][Tt] { fprintf(yyout, "%s", yytext); }
[Ii][Nn][Tt] { fprintf(yyout, "<font color=\"green\">%s</font>", yytext);  }
[A-Za-z][Cc][Hh][Aa][Rr] { fprintf(yyout, "%s", yytext); }
[Cc][Hh][Aa][Rr] { fprintf(yyout, "<font color=\"green\">%s</font>", yytext);  }
[Ff][Ll][Oo][Aa][Tt] { fprintf(yyout, "<font color=\"green\">%s</font>", yytext);  }
[Dd][Oo][Uu][Bb][Ll][Ee] { fprintf(yyout, "<font color=\"green\">%s</font>", yytext);  }
[Ss][Hh][Oo][Rr][Tt] { fprintf(yyout, "<font color=\"green\">%s</font>", yytext);  }
[Ll][Oo][Nn][Gg] { fprintf(yyout, "<font color=\"green\">%s</font>", yytext);  }
[Cc][Oo][Uu][Tt] { fprintf(yyout, "<font color=\"brown\">%s</font>", yytext);  }
[Ee][Nn][Dd][Ll] { fprintf(yyout, "<font color=\"brown\">%s</font>", yytext);  }
"NULL"  { fprintf(yyout, "<font color=\"blue\">%s</font>", yytext);  }
[Vv][Oo][Ii][Dd] { fprintf(yyout, "<font color=\"blue\">%s</font>", yytext);  }

\t { fprintf(yyout, "&nbsp;&nbsp;&nbsp;&nbsp;"); }
\n { fprintf(yyout, "<br/>"); }

%%
int yywrap(void) {  return 1; }
int main() {
	yyout = fopen("strona.html", "wt+"); 
	fprintf(yyout, "<html><body>");
	fprintf(yyout, "<marquee behavior=\"alternate\" bgcolor=\"yellow\">My source code: </marquee><br/>");
	yylex(); 
	fprintf(yyout, yytext);
	fprintf(yyout, "</body></html>");
return yylex();  
}
