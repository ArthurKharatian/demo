-- insert into public.houses(id, built_date, created_at, floors_count, material, name, status, updated_at, user_id)
-- values (2, '1999', '2023-02-02 20:03:12.183292', 4, 0, 'House', 'CREATED', '2023-02-02 20:03:12.183292', 1);

INSERT INTO public.users (id primary key, age, created_at, first_name, gender, last_name, updated_at, email, status)
VALUES (1, 30, '2023-02-02 20:03:12.183292', 'Alex', 'MALE', 'Smith', '2023-02-02 20:03:12.183292', 'test@test.com', 'CREATED');