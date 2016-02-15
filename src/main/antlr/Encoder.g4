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
//  | noninertial
//  | completion
  | constantNonreifiedLine
  | constantReifiedLine
//  | functionValue
//  | formula
  ;	

rangeLine : Range sortId minInt maxInt (NewLine | EOF) ;

sortReifiedLine : Reified Sort sortId parentSortId (NewLine | EOF) ;  
sortNonreifiedLine : Sort sortId parentSortId (NewLine | EOF) ;

constantReifiedLine : Reified Constant sortId constantId* (NewLine | EOF) ;
constantNonreifiedLine : Constant sortId constantId* (NewLine | EOF) ;

sortId : Identifier ;
parentSortId : Identifier ; 
constantId : Identifier ;
minInt : Integer ;
maxInt : Integer ;



Range : 'range' ;
Sort : 'sort' ;
Reified : 'reified' ;
Constant : 'constant' ;


Identifier
  :  ('a'..'z' | 'A'..'Z' | '_') ('a'..'z' | 'A'..'Z' | '_' | '0'..'9')*
  ;

Integer
  :  '0'..'9'+
  ;

NewLine
  :  '\r' '\n'
  |  '\n'
  |  '\r'
  ;

Space
  :  (' ' | '\t') {skip();}
  ;
