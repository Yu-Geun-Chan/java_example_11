package com.koreait;

import java.util.ArrayList;
import java.util.Scanner;

public class TodoController {

    private Scanner sc;
    private ArrayList<Todo> todos;
    private long todosLastId;

    public TodoController(){
        sc = new Scanner(System.in);
        todos = new ArrayList<>();
        todosLastId = 0;
    }

    void add() {
        long id = todosLastId + 1;
        System.out.print("할 일 : ");
        String content = sc.nextLine().trim();

        Todo todo = new Todo(id, content);
        todos.add(todo);
        todosLastId++;

        System.out.printf("%d번 할 일이 생성되었습니다.\n", id);
    }

    void modify() {
        System.out.print("수정할 할 일의 번호 : ");
        long id = Long.parseLong(sc.nextLine().trim());

        // todos를 순회해서 id를 가지고 와서 입력한 id와 첫번째로 일치하는 걸 가져오고, 없다면 null
        Todo foundTodo = todos.stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);

        if (foundTodo == null) {
            System.out.printf("%d번 할 일은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.printf("기존 할 일 : %s\n", foundTodo.getContent());
        System.out.print("새 할일 : ");
        String newContent = sc.nextLine().trim();
        foundTodo.setContent(newContent);

        System.out.printf("%d번 할 일이 수정되었습니다.\n", id);
    }

    void delete() {
        System.out.print("삭제할 할 일의 번호 : ");
        long id = Long.parseLong(sc.nextLine().trim());

        // list는 컬렉션 프레임워크 -> removeIf를 사용하여 ()안의 조건이 참인 모든 요소를 list에서 지운다.
        boolean isRemoved = todos.removeIf(todo -> todo.getId() == id);

        if (!isRemoved) {
            System.out.printf("%d번 할 일은 존재하지 않습니다.\n", id);
            return;
        }

        System.out.printf("%d번 할 일이 삭제되었습니다.\n", id);
    }

    void list() {
        System.out.println("번호  /  내용");

//                    for(Todo todo : todos) {
//                        System.out.printf("%d  /  %s\n", todo.getId(), todo.getContent())
//                    }

        // 위에 주석 처리된 향상된 for문을 forEach을 대체
        todos.forEach(todo -> System.out.printf("%d  /  %s\n", todo.getId(), todo.getContent()));
    }

}
