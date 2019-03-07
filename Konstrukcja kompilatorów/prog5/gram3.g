}
%start parse, S;

S :
  | 'b' R
  | 'a' X
  | 'c' Z
  | 'd'
  | 'e'
  ;

X : 'a' [X | Z]
  ;

Z :
  ;

R :
  | 'b' S
  ;

{
int main(void){
printf("\n");
}
