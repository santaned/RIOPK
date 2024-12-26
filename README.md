РИОПК. Программное средство оценки эффективности HR-службы

# Архитектура

### Контейнерный уровень архитектуры в нотации C4
![c4-C4 Container drawio](https://github.com/user-attachments/assets/9cd72e0b-260b-4a20-ba7c-72c4ffca3114)

### Компонентный уровень архитектуры в нотации C4
![c4-C4 Component drawio](https://github.com/user-attachments/assets/ef1a0306-8f36-4404-bebc-4b226e978854)

### UI Kit
![ui kit](https://github.com/user-attachments/assets/6a5c1e0f-3f6b-4ea6-a074-4004ff5f7d33)

### Диаграмма классов
![диаграмма классов](https://github.com/user-attachments/assets/c522558c-5490-4e87-998f-cc595df0ae80)

### Схема базы данных
![бд](https://github.com/user-attachments/assets/04725253-444c-42a5-b120-9522c98bdecc)

### Диаграмма компонентов
![диаграмма компонентов](https://github.com/user-attachments/assets/c8d3b2f1-a226-4a80-9a8e-0566af5661fd)

### Диаграмма развертывания
![диаграмма развертывания](https://github.com/user-attachments/assets/90a6281f-0fa8-4848-96ae-eb165e373ddd)

### Диаграмма вариантов использования
![диаграмма вариантов использования](https://github.com/user-attachments/assets/237482f3-c563-4717-89a2-cc5141dacaa2)

### Диаграмма деятельности
![диаграмма деятельности](https://github.com/user-attachments/assets/8c418361-dde3-47a3-9d51-74e9498ce519)

### Диаграмма состояний
![диаграмма состояний](https://github.com/user-attachments/assets/2dfc42bc-b8c5-4dfc-b146-58cfdc064f1a)

# Пользовательский интерфейс

## Примеры экранов UI
![image](https://github.com/user-attachments/assets/11e976cb-ac6f-44de-a688-9496efe76f6b)

![image](https://github.com/user-attachments/assets/32fa5d00-477a-45b0-b905-63666253cb5c)

![image](https://github.com/user-attachments/assets/d2d9d7fe-0e5c-4599-8539-629bf47e51d7)

### User-flow диаграмма (Руководитель HR-службы)
![user flow 1](https://github.com/user-attachments/assets/6890fb8b-e6d6-4998-924d-4110f2e3ed32)

### User-flow диаграмма (HR-специалист)
![user flow 2](https://github.com/user-attachments/assets/23c3a4fc-2582-45b1-ae30-8a6d5e38a5b3)

# Документация 

### Документация к API

![image](https://github.com/user-attachments/assets/b9a18bd9-d417-46d6-b3dc-b29bea78de57)

![image](https://github.com/user-attachments/assets/68f92c09-8232-4102-a063-4bb51c38bea9)

![image](https://github.com/user-attachments/assets/98741b6e-6fc9-40d1-b153-82fcec158aca)

![image](https://github.com/user-attachments/assets/599861e5-ac08-4dcf-9851-474de7cc983d)

Расположение документации: https://github.com/santaned/RIOPK_HREfficiency/blob/main/APIDocumentation

# Тестирование

### Примеры юнит-тестов

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

### Результат выполнения юнит-тестов

![image](https://github.com/user-attachments/assets/e8986ca8-0d72-468f-ba3a-455bafb17156)

### Примеры интеграционных тестов

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

### Результат выполнения интеграционных тестов

![image](https://github.com/user-attachments/assets/a135e5ab-c20b-4a68-9541-fdddc636d5ee)

# Безопасность

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailsServiceImp userDetailsServiceImp;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final CustomLogoutHandler logoutHandler;

    public SecurityConfig(UserDetailsServiceImp userDetailsServiceImp,
                          JwtAuthenticationFilter jwtAuthenticationFilter,
                          CustomLogoutHandler logoutHandler) {
        this.userDetailsServiceImp = userDetailsServiceImp;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
        this.logoutHandler = logoutHandler;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req -> req.requestMatchers("/login/**", "/register/**", "/refresh_token/**", "/activate/*", "/**")
                                .permitAll()
                                .requestMatchers("/hr_manager/**").hasAuthority("HRManager")
                                .requestMatchers("/director/**").hasAuthority("HRManager")
                                .anyRequest()
                                .authenticated()
                ).userDetailsService(userDetailsServiceImp)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(
                        e -> e.accessDeniedHandler(
                                        (request, response, accessDeniedException) -> response.setStatus(403)
                                )
                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
                .logout(l -> l
                        .logoutUrl("/logout")
                        .addLogoutHandler(logoutHandler)
                        .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext()
                        ))
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}

# Развертывание

Dockerfile для сервиса на Java:

### Используем официальный образ OpenJDK
FROM openjdk:11-jre-slim

### Устанавливаем рабочую директорию
WORKDIR /app

### Копируем JAR файл приложения
COPY target/my-java-app.jar app.jar

### Указываем команду для запуска приложения
CMD ["java", "-jar", "app.jar"]
Docker-compose:

version: '3.9'

services:
  app:
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "8080:8080"
    environment:
      - DB_HOST=postgres
      - DB_USER=app_user
      - DB_PASSWORD=app_password
      - DB_NAME=app_db
      - REDIS_HOST=redis
      - NATS_URL=nats:4222
    depends_on:
      - postgres
      - redis
      - nats

  postgres:
    image: postgres:15
    environment:
      POSTGRES_USER: app_user
      POSTGRES_PASSWORD: app_password
      POSTGRES_DB: app_db
    volumes:
      - postgres_data:/var/lib/postgresql/data
    ports:
      - "5432:5432"

  redis:
    image: redis:7
    ports:
      - "6379:6379"

  nats:
    image: nats:2.9
    ports:
      - "4222:4222"
    environment:
      - JS_ENABLE=true # Включение JetStream

volumes:
  postgres_data:
 
version: '3.8'

services:
  app:
    build: .
    ports:
      - "8080:8080"
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/mydb
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: password
    depends_on:
      - db

  db:
    image: mysql:8.0
    restart: always
    environment:
      MYSQL_DATABASE: mydb
      MYSQL_ROOT_PASSWORD: password
    ports:
      - "3306:3306"
