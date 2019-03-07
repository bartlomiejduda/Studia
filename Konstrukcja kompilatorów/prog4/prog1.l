%{


int yywrap(void);
%}


%%

a  { return 'a'; }
b  { return 'b'; }

%%
     
int yywrap(void) {  return 1; }

