grammar Encoder;

@header {
   package org.decreasoner4j.antlr.encoder;
} 

encoderInput
	: line+
	;

line
  :  rangeLine
  | sortNonreifiedLine
  | sortReifiedLine
  | noninertialLine
  | completionLine
  | constantNonreifiedLine
  | constantReifiedLine
  | functionValueLine
  | formulaLine
  ;	

rangeLine : Range sortId minInt maxInt (NewLine | EOF) ;
sortNonreifiedLine : Sort sortId parentSortId (NewLine | EOF) ;
sortReifiedLine : Reified Sort sortId parentSortId (NewLine | EOF) ;  
noninertialLine : Noninertial constantId* (NewLine | EOF) ;
completionLine : Completion String String? (NewLine | EOF) ;
constantNonreifiedLine : Constant sortId constantId* (NewLine | EOF) ;
constantReifiedLine : Reified Constant sortId constantId resultSortId argSortId* (NewLine | EOF) ;
functionValueLine : FunctionValue functionId value argument* (NewLine | EOF) ;
formulaLine : Formula formula (NewLine | EOF) ;

formula : LPAREN sourceCode ( term | formula )* RPAREN ; 
sortId : String ;
resultSortId : String ;
argSortId : String ;
label : String ;
pred : String ;
parentSortId : String ; 
constantId : String ;
functionId : String ;
argument : String ;
value : String ;
term : String ;
sourceCode : String;
minInt : String ;
maxInt : String ;

Range : 'range' ;
Sort : 'sort' ;
Reified : 'reified' ;
Constant : 'constant' ;
Noninertial : 'noninertial' ;
Completion : 'completion' ;
FunctionValue : 'function value' ;
Formula : 'formula' ; 

LPAREN : '[' ;

RPAREN : ']' ;

String
  :  ~(' ' | '\t' | '\r' | '\n' | '[' | ']')*
  ;

NewLine
  :  '\r' '\n'
  |  '\n'
  |  '\r'
  ;

Space
  :  (' ' | '\t') {skip();}
  ;
