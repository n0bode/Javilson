#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <wait.h>
#include <signal.h>
#include <string.h>

int main(const int argc, char ** argv){
  char DEBUG = 0;
  
  for(int i = 0; i < argc; i++){
    if (strcmp(argv[i], "-d")) 
      DEBUG = 1;
  } 

  if (DEBUG) puts("#DEBUG ON"); //DEBUG
  puts("Nasce o pai");
  sleep(22); //22
  puts("Nasce o primeiro filho aos 22 anos");
  if (DEBUG) puts("Timeline: 22s"); //DEBUG
  if (fork() == 0){ //Primeiro filho
    sleep(16); //38
    puts("O pai é avô (primeiro filho) aos 38 anos");
    if (DEBUG) puts("Timeline: 38s"); //DEBUG
    
    //Surge o prime neto
    if (fork() == 0){ //Primeiro neto
      sleep(30); //68
      puts("O pai é bisavô (primeiro filho) aos 68 anos");
      if (DEBUG) puts("Timeline: 68s"); //DEBUG
      if(fork() == 0){
        sleep(12); //80
        puts("Bisneto morre aos 12 anos");
        if (DEBUG) puts("Timeline: 80s"); //DEBUG
        exit(0);
      }
      sleep(5); //73s
      puts("O primeiro neto morre ao 35 anos");
      if (DEBUG) puts("Timeline: 73s"); //DEBUG
      exit(0);
    }
    sleep(45); //83
    puts("O primeiro filho morre aos 61 anos");
    if (DEBUG) puts("Timeline: 83s"); //DEBUG
    exit(0);
  }else{ //PAI
    sleep(3); //25
    puts("Nasce o segundo filho aos 25 anos");
    if (DEBUG) puts("Timeline: 25s"); //DEBUG
    if (fork() == 0){ //Segundo filho
      sleep(20); //45
      puts("O pai eh avo (segundo filho) aos 45 anos");
      if (DEBUG) puts("Timeline: 45s"); //DEBUG
      if (fork() == 0){ //Segundo neto
        sleep(33); //78
        puts("O segundo neto morre aos 33 anos");
        if (DEBUG) puts("Timeline: 78s"); //DEBUG
        exit(0);
      }
      sleep(35); // 80
      puts("O segundo filho morre aos 55 anos");
      if (DEBUG) puts("Timeline: 80s"); //DEBUG
      exit(0);
    } 
    sleep(7); //32
    if (fork() == 0){ //Terceiro filho
      puts("O pai tem o terceiro filho aos 32 anos");
      if (DEBUG) puts("Timeline: 32s"); //DEBUG
      sleep(55); //87
      puts("O terceiro filho morre aos 55 anos");
      if (DEBUG) puts("Timeline: 87s"); //DEBUG
      exit(0);
    }
  }
  sleep(58);
  puts("Pai morre aos 90 anos");
  if (DEBUG) puts("Timeline: 90s"); //DEBUG
  return 0;
}
