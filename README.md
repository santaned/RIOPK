РИОПК. Программное средство оценки эффективности HR-службы

# Архитектура

## Контейнерный уровень архитектуры в нотации C4
![c4-C4 Container drawio](https://github.com/user-attachments/assets/9cd72e0b-260b-4a20-ba7c-72c4ffca3114)

## Компонентный уровень архитектуры в нотации C4
![c4-C4 Component drawio](https://github.com/user-attachments/assets/ef1a0306-8f36-4404-bebc-4b226e978854)

## UI Kit
![ui kit](https://github.com/user-attachments/assets/6a5c1e0f-3f6b-4ea6-a074-4004ff5f7d33)

## Диаграмма классов
![диаграмма классов](https://github.com/user-attachments/assets/c522558c-5490-4e87-998f-cc595df0ae80)

## Схема базы данных
![бд](https://github.com/user-attachments/assets/86032051-fea9-4cd6-a1d2-80a849249781)

## Диаграмма компонентов
![диаграмма компонентов](https://github.com/user-attachments/assets/c8d3b2f1-a226-4a80-9a8e-0566af5661fd)

## Диаграмма развертывания
![диаграмма развертывания](https://github.com/user-attachments/assets/90a6281f-0fa8-4848-96ae-eb165e373ddd)

## Диаграмма вариантов использования
![диаграмма вариантов использования](https://github.com/user-attachments/assets/237482f3-c563-4717-89a2-cc5141dacaa2)

## Диаграмма деятельности
![диаграмма деятельности](https://github.com/user-attachments/assets/8c418361-dde3-47a3-9d51-74e9498ce519)

## Диаграмма состояний
![диаграмма состояний](https://github.com/user-attachments/assets/2dfc42bc-b8c5-4dfc-b146-58cfdc064f1a)

# Пользовательский интерфейс

## User-flow диаграмма (Руководитель HR-службы)
![user flow 1](https://github.com/user-attachments/assets/6890fb8b-e6d6-4998-924d-4110f2e3ed32)

## User-flow диаграмма (HR-специалист)
![user flow 2](https://github.com/user-attachments/assets/23c3a4fc-2582-45b1-ae30-8a6d5e38a5b3)

# Документация 

## Документация к API

![image](https://github.com/user-attachments/assets/b9a18bd9-d417-46d6-b3dc-b29bea78de57)

![image](https://github.com/user-attachments/assets/68f92c09-8232-4102-a063-4bb51c38bea9)

![image](https://github.com/user-attachments/assets/98741b6e-6fc9-40d1-b153-82fcec158aca)

![image](https://github.com/user-attachments/assets/599861e5-ac08-4dcf-9851-474de7cc983d)

Расположение документации: [http://localhost:8080/swagger-ui/index.html#/](https://github.com/santaned/RIOPK_HREfficiency/blob/main/APIDocumentation)

# Тестирование

## Примеры юнит-тестов

    @Test
    public void testSaveDepartment() {
        when(departmentService.saveDepartment(department)).thenReturn(department);

        ResponseEntity<Department> response = departmentController.saveDepartment(department);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("HR", response.getBody().getName());
    }

    @Test
    public void testUpdateDepartment() {
        Department updatedDepartment = new Department(1L, "HR", "Updated Human Resources", null, null);
        when(departmentService.updateDepartment(1L, updatedDepartment)).thenReturn(updatedDepartment);

        ResponseEntity<Department> response = departmentController.updateDepartment(1L, updatedDepartment);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated Human Resources", response.getBody().getDescription());
    }

## Результат выполнения юнит-тестов

![image](https://github.com/user-attachments/assets/e8986ca8-0d72-468f-ba3a-455bafb17156)

## Примеры интеграционных тестов

    @Test
    public void testGetDepartment() throws Exception {
        mockMvc.perform(get("/api/v1/department/{id}", department.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(department.getName()))
                .andExpect(jsonPath("$.description").value(department.getDescription()));
    }

    @Test
    public void testDeleteDepartment() throws Exception {
        mockMvc.perform(delete("/api/v1/department/{id}", department.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(department.getName()))
                .andExpect(jsonPath("$.description").value(department.getDescription()));
    }

## Результат выполнения интеграционных тестов

![image](https://github.com/user-attachments/assets/a135e5ab-c20b-4a68-9541-fdddc636d5ee)

