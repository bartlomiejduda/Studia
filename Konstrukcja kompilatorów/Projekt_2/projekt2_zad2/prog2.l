%{
#include <stdio.h>
int yylex(void);
int yywrap(void);

%}

%%


^"//".*$  { printf("\033[22;90m%s\033[0m"); }
"/*".*"*/"  { printf("\033[22;90m%s\033[0m"); }

[Ii][Mm][Ii][Ee]": ".*[0-9]+.*$ { printf("%s \033[22;31m//imie niepoprawne, nie moze zawierac cyfr\033[0m"); }
[Nn][Aa][Zz][Ww][Ii][Ss][Kk][Oo]": ".*[0-9]+.*$ { printf("%s \033[22;31m//nazwisko niepoprawne, nie moze zawierac cyfr\033[0m"); }
[Mm][Ii][Aa][Ss][Tt][Oo]": ".*[0-9]+.*$ { printf("%s \033[22;31m//miasto niepoprawne, nie moze zawierac cyfr\033[0m"); }
[Tt][Ee][Ll][Ee][Ff][Oo][Nn]": ".*[A-Za-z]+.*$ { printf("%s \033[22;31m//telefon niepoprawny, nie moze zawierac liter\033[0m"); }
[Kk][Oo][Dd]" "[Pp][Oo][Cc][Zz][Tt][Oo][Ww][Yy]": ".*[A-Za-z]+.*$ { printf("%s \033[22;31m//kod pocztowy niepoprawny, nie moze zawierac liter\033[0m"); }
[Dd][Aa][Tt][Aa]" "[Uu][Rr][Oo][Dd][Zz][Ee][Nn][Ii][Aa]": ".*[A-Za-z]+.*$ { printf("%s \033[22;31m//data urodzenia niepoprawna, nie moze zawierac liter\033[0m"); }

[Ii][Mm][Ii][Ee]": ".*[!@#$%^&*()-+?]+.*$ { printf("%s \033[22;95m//imie niepoprawne, zawiera niedozwolone znaki\033[0m"); }
[Nn][Aa][Zz][Ww][Ii][Ss][Kk][Oo]": ".*[!@#$%^&*()-+?]+.*$ { printf("%s \033[22;95m//nazwisko niepoprawne, zawiera niedozwolone znaki\033[0m"); }
[Mm][Ii][Aa][Ss][Tt][Oo]": ".*[!@#$%^&*()-+?]+.*$ { printf("%s \033[22;95m//miasto niepoprawne, zawiera niedozwolone znaki\033[0m"); }
[Tt][Ee][Ll][Ee][Ff][Oo][Nn]": ".*"$"+.*$ { printf("%s \033[22;95m//telefon niepoprawny, zawiera niedozwolone znaki\033[0m"); }
[Kk][Oo][Dd]" "[Pp][Oo][Cc][Zz][Tt][Oo][Ww][Yy]": ".*[!@#$%^&*()-+?]+.*$ { printf("%s \033[22;95m//kod pocztowy niepoprawny, zawiera niedozwolone znaki\033[0m"); }
[Dd][Aa][Tt][Aa]" "[Uu][Rr][Oo][Dd][Zz][Ee][Nn][Ii][Aa]": ".*[!@#$%^&*()-+?]+.*$ { printf("%s \033[22;95m//data urodzenia niepoprawna, zawiera niedozwolone znaki\033[0m"); }

[Ii][Mm][Ii][Ee]": "[a-z][A-Za-z]* { printf("%s \033[22;33m//imie niepoprawne, prosze zaczac od wielkiej litery\033[0m"); }
[Nn][Aa][Zz][Ww][Ii][Ss][Kk][Oo]": "[a-z][A-Za-z]* { printf("%s \033[22;33m//nazwisko niepoprawne, prosze zaczac od wielkiej litery\033[0m"); }
[Mm][Ii][Aa][Ss][Tt][Oo]": "[a-z][A-Za-z]*" "*[A-Za-z]* { printf("%s \033[22;33m//miasto niepoprawne, prosze zaczac od wielkiej litery\033[0m"); }


[Ii][Mm][Ii][Ee]": "[A-Z]$ { printf("%s \033[22;93m//imie za krotkie\033[0m"); }
[Nn][Aa][Zz][Ww][Ii][Ss][Kk][Oo]": "[A-Z]$ { printf("%s \033[22;93m//nazwisko za krotkie\033[0m"); }


[Ii][Mm][Ii][Ee]": "[A-Z][A-Za-z]+ { printf("%s \033[22;32m//imie poprawne\033[0m"); }
[Nn][Aa][Zz][Ww][Ii][Ss][Kk][Oo]": "[A-Z][A-Za-z]+ { printf("%s \033[22;32m//nazwisko poprawne\033[0m"); }
[Mm][Ii][Aa][Ss][Tt][Oo]": "[A-Z][A-Za-z]*" "*[A-Za-z]* { printf("%s \033[22;32m//miasto poprawne\033[0m"); }
[Tt][Ee][Ll][Ee][Ff][Oo][Nn]": "[+]{0,1}[1-9][0-9][0-9]" "*[0-9][0-9][0-9]" "*[0-9][0-9][0-9] { printf("%s \033[22;32m//telefon poprawny\033[0m"); }
[Kk][Oo][Dd]" "[Pp][Oo][Cc][Zz][Tt][Oo][Ww][Yy]": "[0-9][0-9][ -][0-9][0-9][0-9] { printf("%s \033[22;32m//kod pocztowy poprawny\033[0m"); }
[Ee]"-"[Mm][Aa][Ii][Ll]": "[A-Za-z0-9._-]+@[A-Za-z0-9._]+\.[A-Za-z]{2,4} { printf("%s \033[22;32m//e-mail poprawny\033[0m"); }
[Dd][Aa][Tt][Aa]" "[Uu][Rr][Oo][Dd][Zz][Ee][Nn][Ii][Aa]": "[0-3][[0-9]"."[0-1][0-9]"."[1-2][0-9][0-9][0-9] { printf("%s \033[22;32m//data urodzenia poprawna\033[0m"); }




%%
int yywrap(void) {  return 1; }
int main() { return yylex(); }
