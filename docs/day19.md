Day 19's grammar is pretty limited. A rule is either groups of one or two subrules, or a 
terminal value.

```
8: 42
11: 42 31
23: 89 47 | 16 12
16: "b"
```

These rules can be represented as a [context-free grammar](https://en.wikipedia.org/wiki/Context-free_grammar).

Few options for parsing CFGs:
* Recursive descent
* CKY parser
* Earley parser

I chose the Earley parser, mostly because I wasn't familiar with it.
But it's also very efficient. It can run in linear time on deterministic context-free grammars.

Helpful references:
* https://loup-vaillant.fr/tutorials/earley-parsing/
* https://joshuagrams.github.io/pep/
* https://loup-vaillant.fr/tutorials/earley-parsing/recogniser
