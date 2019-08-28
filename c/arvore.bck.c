#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <wait.h>
#include <signal.h>

typedef unsigned int uint;

int main(const int argc, char ** argv){
  int lock = 0;

  puts("Nasce o pai");
  sleep(22); //22
  puts("Nasce o primeiro filho 22s");
  if (fork() == 0){ //Primeiro filho
    sleep(16); //38
    puts("O pai é avô (primeiro filho) 38s");
    if (fork() == 0){ //Primeiro neto
      sleep(35); //73s
      puts("O primeiro neto morre");
      exit(0);
    }
    sleep(23); //61
    puts("O primeiro filho morre 61");
    sleep(7);
    puts("O pai é bisavô (primeiro filho 68s");
    if(fork() == 0){
      sleep(12); //
      puts("Bisneto morre 80");
      exit(0);
    }
    exit(0);
  }else{ //PAI
    sleep(3); //25
    if (fork() == 0){ //Segundo filho
      puts("Nasce o segundo filho 25");
      sleep(20); //45
      puts("O pai é avô (segundo filho) 45");
      if (fork() == 0){ //Segundo neto
        sleep(33);
        puts("O segundo neto morre 78");
        exit(0);
      }
      sleep(10); // 55
      puts("O segundo filho morre 55");
      exit(0);
    } 
    sleep(7); //32
    if (fork() == 0){ //Terceiro filho
      puts("O pai tem o terceiro filho 32");
      sleep(23); //55
      puts("O terceiro filho morre 55s");
      exit(0);
    }
  }
  sleep(58);
  puts("Pai morre");
  return 0;
}
