#include <iostream>
#include <assert.h>

struct Element{
    void* _data;
    Element* _next;

    public:
    Element(){}

    Element(void* data, Element* next){
        this->_data = data;
        this->_next = next;
    }

    Element* next(){
        return this->_next;
    }
    
    void setNext(Element* next){
        this->_next = next;
    }

    void* data(){
        return this->_data;
    }
};

class List{
    private:
        Element* _head;
        Element* _tail; 
    public: 
        List(){
            //this->_elements = new Element*[0];
            this->_head = NULL;
            this->_tail = NULL;
        }

        void PushFront(void* data){
            Element* niw = new Element(data, this->_head);
            //this->addElement(niw);
            if (this->_head == NULL){
                this->_tail = niw;
            }
            this->_head = niw;
        }

        void PushBack(void* data){
            Element* niw = new Element(data, NULL);
            if (this->_tail != NULL){
                this->_tail->setNext(niw);
            }else{
                this->_head = niw;
            }
            this->_tail = niw;
        }

        void Pop(void* data){
            Element *ptr= this->head();
            Element *pre= NULL;

            for(;ptr != NULL && ptr->data() != data; ptr = ptr->next()){
                pre=ptr;
            }
            assert(ptr != NULL);
            
            if(ptr == this->tail()){
                this->_tail = pre;
            }

            if(ptr == this->head()){
                this->_head = ptr->next();
            }else{
                pre->setNext(ptr->next());
            }
        }

        Element* head(){
            return this->_head;
        }

        Element* tail(){
            return this->_tail;
        }

        void print(){
            std::cout << "[ ";
            for (Element* iter = this->head(); iter != NULL; iter=iter->next()){
                if (iter != this->tail())
                    std::cout << *(int*)iter->data() << ", ";
                else
                    std::cout << *(int*)iter->data();
            }
            std::cout << "]" << std::endl;
        }
};


int main(int argc, char ** argv){
    int* v0 = new int(0);
    int* v1 = new int(1);
    int* v2 = new int(2);
    int* v3 = new int(3);
    int* v4 = new int(4);
    int* v5 = new int(5);

    List list;
    list.PushFront(v0);
    list.PushBack(v1);
    list.PushFront(v2);
    list.PushBack(v3);
    list.PushBack(v5);

    list.print();
    list.Pop(v2);
    list.print();
    list.Pop(v0);
    list.print();
    list.Pop(v4);
    list.print();
    return 0;
}
