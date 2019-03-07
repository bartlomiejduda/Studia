/* LLgen generated code from source gramatyka.g */
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
# line 1 "gramatyka.g"

#include<stdio.h>
#include<stdlib.h>
int LLlval;
#if LL_ANSI_C
static void LL1_RL(
# line 9 "gramatyka.g"
int n) ;
static void LL2_L(
# line 13 "gramatyka.g"
int n) ;
#else
static LL1_RL();
static LL2_L();
#endif
static
#if LL_ANSI_C
void
#endif
LL1_RL(
#if LL_ANSI_C
# line 9 "gramatyka.g"
int n)  
#else
# line 9 "gramatyka.g"
 n) int n;
#endif
{
LLread();
goto L_2; /* so that the label is used for certain */
L_2: ;
switch(LLcsymb) {
case /* ',' */ 3 : ;
LL_SAFE(',');
LL_NOSCANDONE(num);
# line 9 "gramatyka.g"
{if(n==0)printf("%d",LLlval); if (n!=-1)n--; else n=-1;}
LL1_RL(
# line 9 "gramatyka.g"
n);
break;
case /*  EOFILE  */ 0 : ;
goto L_3;
L_3: ;
LLtdecr(3);
break;
default: if (LLskip()) goto L_2;
goto L_3;
}
}
static
#if LL_ANSI_C
void
#endif
LL2_L(
#if LL_ANSI_C
# line 13 "gramatyka.g"
int n)  
#else
# line 13 "gramatyka.g"
 n) int n;
#endif
{
LLtincr(3);
LL_NOSCANDONE(num);
# line 13 "gramatyka.g"
{if(n==0)printf("[%d]",LLlval);}
LL1_RL(
# line 13 "gramatyka.g"
n-1);
}
#if LL_ANSI_C
void
#endif
LL0_S(
#if LL_ANSI_C
void
#endif
) {
# line 17 "gramatyka.g"
int n;
LLtincr(4);
LLsincr(0);
LL_SCANDONE(num);
# line 17 "gramatyka.g"
{n=LLlval;}
LLtdecr(4);
LL_NOSCANDONE(':');
LLsdecr(0);
LL2_L(
# line 17 "gramatyka.g"
n);
}

# line 19 "gramatyka.g"

main(){
printf("\n");
parse();
}
LLmessage(int tk){
	if (tk == -1) printf("oczekiwany koniec pliku \n");
	else if (tk == 0){
		if (LLsymb <256) printf("Nieoczekiwany token (%c) usuniety\n",LLsymb);
		else printf("Nieoczekiwany token o nuerze (%d) usuniêty \n",LLsymb);
		}
	else printf("Oczekiwano tokenu o numerze (%d)\n",tk);
	}

