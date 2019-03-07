/* LLgen generated code from source gram1.g */
#include "Lpars.h"
#define LL_LEXI yylex
#define LLNOFIRSTS
#if __STDC__ || __cplusplus
#define LL_ANSI_C 1
#endif
#define LL_LEXI yylex
/* $Id: incl,v 2.13 1997/02/21 15:44:09 ceriel Exp $ */
#ifdef LL_DEBUG
#include <assert.h>
#include <stdio.h>
#define LL_assert(x)	assert(x)
#else
#define LL_assert(x)	/* nothing */
#endif

extern int LLsymb;

#define LL_SAFE(x)	/* Nothing */
#define LL_SSCANDONE(x)	if (LLsymb != x) LLsafeerror(x)
#define LL_SCANDONE(x)	if (LLsymb != x) LLerror(x)
#define LL_NOSCANDONE(x) LLscan(x)
#ifdef LL_FASTER
#define LLscan(x)	if ((LLsymb = LL_LEXI()) != x) LLerror(x)
#endif

extern unsigned int LLscnt[];
extern unsigned int LLtcnt[];
extern int LLcsymb;

#if LL_NON_CORR
extern int LLstartsymb;
#endif

#define LLsdecr(d)	{LL_assert(LLscnt[d] > 0); LLscnt[d]--;}
#define LLtdecr(d)	{LL_assert(LLtcnt[d] > 0); LLtcnt[d]--;}
#define LLsincr(d)	LLscnt[d]++
#define LLtincr(d)	LLtcnt[d]++

#if LL_ANSI_C
extern int LL_LEXI(void);
extern void LLread(void);
extern int LLskip(void);
extern int LLnext(int);
extern void LLerror(int);
extern void LLsafeerror(int);
extern void LLnewlevel(unsigned int *);
extern void LLoldlevel(unsigned int *);
#ifndef LL_FASTER
extern void LLscan(int);
#endif
#ifndef LLNOFIRSTS
extern int LLfirst(int, int);
#endif
#if LL_NON_CORR
extern void LLnc_recover(void);
#endif
#else /* not LL_ANSI_C */
extern LLread();
extern int LLskip();
extern int LLnext();
extern LLerror();
extern LLsafeerror();
extern LLnewlevel();
extern LLoldlevel();
#ifndef LL_FASTER
extern LLscan();
#endif
#ifndef LLNOFIRSTS
extern int LLfirst();
#endif
#if LL_NON_CORR
extern LLnc_recover();
#endif
#endif /* not LL_ANSI_C */
# line 1 "gram1.g"

  #include <stdio.h>
  #include <stdlib.h>
  int parse();
#if LL_ANSI_C
#else
#endif
#if LL_ANSI_C
void
#endif
LL0_spec(
#if LL_ANSI_C
void
#endif
) {
LLtincr(2);
LLtincr(3);
for (;;) {
LL_SCANDONE('a');
LLread();
goto L_1;
L_1 : {switch(LLcsymb) {
case /* 'b' */ 3 : ;
break;
default:{int LL_1=LLnext(97);
;if (!LL_1) {
break;
}
else if (LL_1 & 1) goto L_1;}
case /* 'a' */ 2 : ;
continue;
}
}
LLtdecr(2);
break;
}
for (;;) {
LL_SSCANDONE('b');
LLread();
goto L_2;
L_2 : {switch(LLcsymb) {
case /*  EOFILE  */ 0 : ;
break;
default:{int LL_2=LLnext(98);
;if (!LL_2) {
break;
}
else if (LL_2 & 1) goto L_2;}
case /* 'b' */ 3 : ;
continue;
}
}
LLtdecr(3);
break;
}
# line 9 "gram1.g"
{ printf("OK"); }
}

# line 13 "gram1.g"
 
   int main(){
    printf("\n");
    parse();
    printf("\n");
    return 0;
   }

void LLmessage(int tk)
{
  printf("\n blad numer (%d) \n", tk);
  exit(1);
}


