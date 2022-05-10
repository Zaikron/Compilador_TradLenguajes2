/* The following code was generated by JFlex 1.4.3 on 09/05/22 20:42 */


package Code;
import compilerTools.Token;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.4.3
 * on 09/05/22 20:42 from the specification file
 * <tt>C:/Users/AnthonySandoval/Documents/NetBeansProjects/Compilador/src/Code/Lexer.flex</tt>
 */
class Lexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\11\0\1\3\1\2\1\0\1\3\1\1\22\0\1\3\1\46\1\32"+
    "\1\61\1\62\1\51\1\44\1\33\1\52\1\53\1\5\1\42\1\40"+
    "\1\43\1\0\1\4\1\10\11\7\1\0\1\60\1\50\1\41\1\47"+
    "\2\0\32\6\1\56\1\0\1\57\1\0\1\6\1\0\1\11\1\15"+
    "\1\16\1\17\1\35\1\27\1\31\1\20\1\24\2\6\1\21\1\13"+
    "\1\25\1\22\1\34\1\6\1\30\1\12\1\26\1\37\1\23\1\36"+
    "\1\14\2\6\1\54\1\45\1\55\103\0\1\6\7\0\1\6\3\0"+
    "\1\6\3\0\1\6\1\0\1\6\6\0\1\6\1\0\1\6\4\0"+
    "\1\6\7\0\1\6\3\0\1\6\3\0\1\6\1\0\1\6\6\0"+
    "\1\6\1\0\1\6\uff03\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\2\2\2\3\1\4\2\5\13\4\1\6"+
    "\1\7\3\4\1\10\1\11\2\3\2\12\1\13\2\14"+
    "\1\1\1\15\1\16\1\17\1\20\1\21\1\22\1\23"+
    "\1\24\1\25\1\2\1\0\1\26\1\4\1\27\1\4"+
    "\1\30\3\4\1\30\1\31\3\4\1\32\10\4\1\14"+
    "\1\33\1\12\2\0\1\34\1\35\2\4\1\36\3\4"+
    "\1\37\3\4\1\40\4\4\1\0\1\2\1\4\1\41"+
    "\1\42\1\43\1\4\1\44\3\4\1\45\3\4\1\46"+
    "\1\4\1\47\1\50\1\51\1\52\1\53";

  private static int [] zzUnpackAction() {
    int [] result = new int[112];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\63\0\146\0\63\0\231\0\314\0\377\0\u0132"+
    "\0\63\0\u0165\0\u0198\0\u01cb\0\u01fe\0\u0231\0\u0264\0\u0297"+
    "\0\u02ca\0\u02fd\0\u0330\0\u0363\0\63\0\63\0\u0396\0\u03c9"+
    "\0\u03fc\0\63\0\u042f\0\u0462\0\u0495\0\u04c8\0\u04fb\0\u042f"+
    "\0\u052e\0\u0561\0\314\0\63\0\63\0\63\0\63\0\63"+
    "\0\63\0\63\0\63\0\63\0\u0594\0\u05c7\0\63\0\u05fa"+
    "\0\377\0\u062d\0\377\0\u0660\0\u0693\0\u06c6\0\u06f9\0\377"+
    "\0\u072c\0\u075f\0\u0792\0\377\0\u07c5\0\u07f8\0\u082b\0\u085e"+
    "\0\u0891\0\u08c4\0\u08f7\0\u092a\0\63\0\63\0\63\0\u095d"+
    "\0\u0990\0\377\0\377\0\u09c3\0\u09f6\0\377\0\u0a29\0\u0a5c"+
    "\0\u0a8f\0\377\0\u0ac2\0\u0af5\0\u0b28\0\377\0\u0b5b\0\u0b8e"+
    "\0\u0bc1\0\u0bf4\0\u0c27\0\u095d\0\u0c5a\0\377\0\377\0\377"+
    "\0\u0c8d\0\377\0\u0cc0\0\u0cf3\0\u0d26\0\377\0\u0d59\0\u0d8c"+
    "\0\u0dbf\0\377\0\u0df2\0\377\0\377\0\377\0\377\0\377";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[112];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\2\1\3\2\4\1\5\1\6\1\7\1\10\1\11"+
    "\1\12\1\13\1\14\1\7\1\15\1\16\1\17\3\7"+
    "\1\20\1\21\1\7\1\22\1\23\1\24\1\7\1\25"+
    "\1\26\1\27\1\30\1\31\1\7\1\32\1\33\1\34"+
    "\1\35\1\36\1\37\1\40\1\41\1\42\1\43\1\44"+
    "\1\45\1\46\1\47\1\50\1\51\1\52\1\53\1\54"+
    "\65\0\1\4\64\0\1\55\1\56\33\0\1\57\62\0"+
    "\1\57\27\0\24\7\2\0\4\7\32\0\2\10\60\0"+
    "\4\7\1\60\1\7\1\61\2\7\1\62\2\63\10\7"+
    "\2\0\4\7\31\0\20\7\1\64\3\7\2\0\4\7"+
    "\31\0\3\7\1\65\10\7\1\66\7\7\2\0\4\7"+
    "\31\0\6\7\1\61\3\7\2\63\10\7\2\0\4\7"+
    "\31\0\6\7\1\61\3\7\1\67\1\63\10\7\2\0"+
    "\4\7\31\0\6\7\1\61\3\7\2\63\1\70\7\7"+
    "\2\0\4\7\31\0\14\7\1\71\7\7\2\0\4\7"+
    "\31\0\5\7\1\72\11\7\1\73\1\7\1\74\2\7"+
    "\2\0\4\7\31\0\22\7\1\75\1\7\2\0\4\7"+
    "\31\0\3\7\1\76\7\7\1\77\1\100\7\7\2\0"+
    "\4\7\31\0\24\7\2\0\1\7\1\101\2\7\31\0"+
    "\22\7\1\102\1\7\2\0\4\7\31\0\13\7\1\103"+
    "\10\7\2\0\4\7\31\0\12\7\1\104\11\7\2\0"+
    "\4\7\64\0\1\105\62\0\1\57\1\106\61\0\1\57"+
    "\1\0\1\106\63\0\1\107\63\0\1\107\56\0\1\105"+
    "\5\0\1\105\54\0\1\105\6\0\1\105\12\0\1\55"+
    "\1\3\1\4\60\55\5\110\1\111\55\110\6\0\5\7"+
    "\1\112\16\7\2\0\4\7\31\0\11\7\1\113\12\7"+
    "\2\0\4\7\31\0\22\7\1\114\1\7\2\0\4\7"+
    "\31\0\16\7\1\115\5\7\2\0\4\7\31\0\15\7"+
    "\1\116\6\7\2\0\4\7\31\0\3\7\1\117\20\7"+
    "\2\0\4\7\31\0\16\7\1\120\5\7\2\0\4\7"+
    "\31\0\24\7\2\0\1\121\3\7\31\0\20\7\1\122"+
    "\3\7\2\0\4\7\31\0\24\7\2\0\3\7\1\123"+
    "\31\0\13\7\1\124\10\7\2\0\4\7\31\0\14\7"+
    "\1\125\7\7\2\0\4\7\31\0\22\7\1\126\1\7"+
    "\2\0\4\7\31\0\20\7\1\127\3\7\2\0\4\7"+
    "\31\0\16\7\1\130\5\7\2\0\4\7\31\0\4\7"+
    "\1\131\17\7\2\0\4\7\31\0\16\7\1\132\5\7"+
    "\2\0\4\7\23\0\5\110\1\133\61\110\1\134\1\133"+
    "\55\110\6\0\16\7\1\135\5\7\2\0\4\7\31\0"+
    "\17\7\1\136\4\7\2\0\4\7\31\0\22\7\1\137"+
    "\1\7\2\0\4\7\31\0\11\7\1\140\12\7\2\0"+
    "\4\7\31\0\14\7\1\141\7\7\2\0\4\7\31\0"+
    "\24\7\2\0\1\7\1\142\2\7\31\0\4\7\1\123"+
    "\17\7\2\0\4\7\31\0\3\7\1\143\20\7\2\0"+
    "\4\7\31\0\24\7\2\0\3\7\1\144\31\0\17\7"+
    "\1\145\4\7\2\0\4\7\31\0\24\7\2\0\1\7"+
    "\1\146\2\7\31\0\13\7\1\147\10\7\2\0\4\7"+
    "\23\0\4\110\1\4\1\133\55\110\6\0\17\7\1\150"+
    "\4\7\2\0\4\7\31\0\22\7\1\151\1\7\2\0"+
    "\4\7\31\0\20\7\1\152\3\7\2\0\4\7\31\0"+
    "\22\7\1\153\1\7\2\0\4\7\31\0\20\7\1\154"+
    "\3\7\2\0\4\7\31\0\24\7\2\0\1\7\1\155"+
    "\2\7\31\0\23\7\1\156\2\0\4\7\31\0\20\7"+
    "\1\157\3\7\2\0\4\7\31\0\17\7\1\160\4\7"+
    "\2\0\4\7\23\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[3621];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unkown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\1\1\1\11\4\1\1\11\13\1\2\11"+
    "\3\1\1\11\11\1\11\11\1\1\1\0\1\11\25\1"+
    "\3\11\2\0\21\1\1\0\25\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[112];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
    private Token token(String lexeme, String lexicalComp, int line, int column){
        return new Token(lexeme, lexicalComp, line+1, column+1);
    }


  /**
   * Creates a new scanner
   * There is also a java.io.InputStream version of this constructor.
   *
   * @param   in  the java.io.Reader to read input from.
   */
  Lexer(java.io.Reader in) {
    this.zzReader = in;
  }

  /**
   * Creates a new scanner.
   * There is also java.io.Reader version of this constructor.
   *
   * @param   in  the java.io.Inputstream to read input from.
   */
  Lexer(java.io.InputStream in) {
    this(new java.io.InputStreamReader(in));
  }

  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x10000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 188) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzCurrentPos*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
    }

    /* finally: fill the buffer with new input */
    int numRead = zzReader.read(zzBuffer, zzEndRead,
                                            zzBuffer.length-zzEndRead);

    if (numRead > 0) {
      zzEndRead+= numRead;
      return false;
    }
    // unlikely but not impossible: read 0 characters, but not at end of stream    
    if (numRead == 0) {
      int c = zzReader.read();
      if (c == -1) {
        return true;
      } else {
        zzBuffer[zzEndRead++] = (char) c;
        return false;
      }     
    }

	// numRead < 0
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public Token yylex() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      boolean zzR = false;
      for (zzCurrentPosL = zzStartRead; zzCurrentPosL < zzMarkedPosL;
                                                             zzCurrentPosL++) {
        switch (zzBufferL[zzCurrentPosL]) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn++;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL)
            zzInput = zzBufferL[zzCurrentPosL++];
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = zzBufferL[zzCurrentPosL++];
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          int zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
        case 38: 
          { return token(yytext(), "FLOAT", yyline, yycolumn);
          }
        case 44: break;
        case 11: 
          { return token(yytext(), "NEG", yyline, yycolumn);
          }
        case 45: break;
        case 8: 
          { return token(yytext(), "COMA", yyline, yycolumn);
          }
        case 46: break;
        case 19: 
          { return token(yytext(), "PUNTO_COMA", yyline, yycolumn);
          }
        case 47: break;
        case 41: 
          { return token(yytext(), "CADENA", yyline, yycolumn);
          }
        case 48: break;
        case 26: 
          { return token(yytext(), "IF", yyline, yycolumn);
          }
        case 49: break;
        case 2: 
          { /*Ignorar*/
          }
        case 50: break;
        case 36: 
          { return token(yytext(), "OP_BOOL", yyline, yycolumn);
          }
        case 51: break;
        case 35: 
          { return token(yytext(), "VOID", yyline, yycolumn);
          }
        case 52: break;
        case 33: 
          { return token(yytext(), "MAIN", yyline, yycolumn);
          }
        case 53: break;
        case 42: 
          { return token(yytext(), "IMPORT", yyline, yycolumn);
          }
        case 54: break;
        case 9: 
          { return token(yytext(), "ASIGNACION", yyline, yycolumn);
          }
        case 55: break;
        case 4: 
          { return token(yytext(), "IDENTIFICADOR", yyline, yycolumn);
          }
        case 56: break;
        case 21: 
          { return token(yytext(), "SIMB_SENT", yyline, yycolumn);
          }
        case 57: break;
        case 28: 
          { return token(yytext(), "ASM", yyline, yycolumn);
          }
        case 58: break;
        case 43: 
          { return token(yytext(), "RETURN", yyline, yycolumn);
          }
        case 59: break;
        case 34: 
          { return token(yytext(), "CHAR", yyline, yycolumn);
          }
        case 60: break;
        case 15: 
          { return token(yytext(), "LLAVE_A", yyline, yycolumn);
          }
        case 61: break;
        case 7: 
          { return token(yytext(), "C_SIMPLE", yyline, yycolumn);
          }
        case 62: break;
        case 20: 
          { return token(yytext(), "GATO", yyline, yycolumn);
          }
        case 63: break;
        case 16: 
          { return token(yytext(), "LLAVE_C", yyline, yycolumn);
          }
        case 64: break;
        case 22: 
          { return token(yytext(), "OP_ATRIBUCION", yyline, yycolumn);
          }
        case 65: break;
        case 37: 
          { return token(yytext(), "ELSE", yyline, yycolumn);
          }
        case 66: break;
        case 13: 
          { return token(yytext(), "PARENTESIS_A", yyline, yycolumn);
          }
        case 67: break;
        case 39: 
          { return token(yytext(), "PRINT", yyline, yycolumn);
          }
        case 68: break;
        case 10: 
          { return token(yytext(), "OP_LOGICO", yyline, yycolumn);
          }
        case 69: break;
        case 29: 
          { return token(yytext(), "ADD", yyline, yycolumn);
          }
        case 70: break;
        case 25: 
          { return token(yytext(), "DO", yyline, yycolumn);
          }
        case 71: break;
        case 6: 
          { return token(yytext(), "COMILLAS", yyline, yycolumn);
          }
        case 72: break;
        case 12: 
          { return token(yytext(), "OP_RELACIONAL", yyline, yycolumn);
          }
        case 73: break;
        case 31: 
          { return token(yytext(), "INT", yyline, yycolumn);
          }
        case 74: break;
        case 24: 
          { return token(yytext(), "REG_8", yyline, yycolumn);
          }
        case 75: break;
        case 14: 
          { return token(yytext(), "PARENTESIS_C", yyline, yycolumn);
          }
        case 76: break;
        case 23: 
          { return token(yytext(), "REG_16", yyline, yycolumn);
          }
        case 77: break;
        case 30: 
          { return token(yytext(), "MOV", yyline, yycolumn);
          }
        case 78: break;
        case 1: 
          { return token(yytext(), "ERROR", yyline, yycolumn);
          }
        case 79: break;
        case 17: 
          { return token(yytext(), "CORCHETE_A", yyline, yycolumn);
          }
        case 80: break;
        case 27: 
          { return token(yytext(), "OP_INCREMENTO", yyline, yycolumn);
          }
        case 81: break;
        case 32: 
          { return token(yytext(), "FOR", yyline, yycolumn);
          }
        case 82: break;
        case 5: 
          { return token(yytext(), "NUMERO", yyline, yycolumn);
          }
        case 83: break;
        case 3: 
          { return token(yytext(), "OP_ARIT", yyline, yycolumn);
          }
        case 84: break;
        case 40: 
          { return token(yytext(), "WHILE", yyline, yycolumn);
          }
        case 85: break;
        case 18: 
          { return token(yytext(), "CORCHETE_C", yyline, yycolumn);
          }
        case 86: break;
        default: 
          if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
            zzAtEOF = true;
            return null;
          } 
          else {
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}
