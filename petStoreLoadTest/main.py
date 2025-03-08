from locust import HttpUser, between, task


class WebsiteUser(HttpUser):
    wait_time = between(5, 15)

    @task
    def logout(self):
        self.client.get("/v2/user/logout")

    @task
    def login(self):
        self.client.get("/v2/user/login?username=ayla&password=1234")

    @task
    def get_user_info(self):
        self.client.get("/v2/user/firdes")

    @task
    def user_create(self):
        payload = {
            "id": 154673835,
            "username": "firdes3",
            "firstName": "firdes1",
            "lastName": "oyaf",
            "email": "sahin92@gmail.com",
            "password": "test.123",
            "phone": "5541371166",
            "userStatus": 4
        }
        self.client.post("/v2/user", json=payload)

    @task
    def delete_user(self):
        self.client.delete("/v2/user/firdes1")

    @task
    def update_user(self):
        payload1 = {
            "id": 1097573456,
            "username": "ayla",
            "firstName": "ae",
            "lastName": "otar",
            "email": "test@test.com",
            "password": "1234",
            "phone": "55555",
            "userStatus": 3
        }
        header_payload = {
            "accept": "application/json",
            "Content-Type": "application/json",
        }
        self.client.put("/v2/user/ayla", json=payload1, headers=header_payload)

    @task
    def pet_create(self):
        payload2 = {
            "id": 1,
            "category": {
                "id": 2,
                "name": "çiko"
            },
            "name": "doggie",
            "photoUrls": [
                "https://picsum.photos/id/237/200/300"
            ],
            "tags": [
                {
                    "id": 1,
                    "name": "friendly"
                }
            ],
            "status": "available"
        }
        self.client.post("/v2/pet", json=payload2)

        @task
        def pet_update(self):
            payload3 = {
                    "id": 1,
                    "category": {
                        "id": 2,
                        "name": "çiko"
                    },
                    "name": "doggie",
                    "photoUrls": [
                        "https://picsum.photos/200/300?grayscale"
                    ],
                    "tags": [
                        {
                            "id": 2,
                            "name": "vaccinated"
                        }
                    ],
                    "status": "available"
                }

            header_payload = {
                "accept": "application/json",
                "Content-Type": "application/json",
            }
            self.client.put("/v2/pet", json=payload3, headers=header_payload)

            @task
            def delete_pet(self):
                self.client.delete("/v2/pet/5")
