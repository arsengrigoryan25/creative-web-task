# Create DataBase

In MySQL create 'creative_web_task' database

# Creat comment
POST: localhost:8080/comment/
## Request

```python
{
    "comment":"comment 1"
}
```
## Response
```python
{
    "id": 1,
    "comment": "comment 1",
    "time": "2020-12-21T05:26:33.993+0000"
}
```
or 
```python
{
    "id": 1,
    "comment": "comment 1",
    "time": "2020-12-21T05:27:32.983+0000",
    "commentDeleted": true
}
```
when not created comment and notification

# Get page
GET: localhost:8080/comment/0
## Request

## Response
```python
{
    "currentPage": 0,
    "totalPage": 1,
    "resultCurrentPage": [
        {
            "id": 1,
            "comment": "comment 1",
            "time": "2020-12-21T05:26:33.000+0000"
        },
        {
            "id": 3,
            "comment": "comment 3",
            "time": "2020-12-21T05:33:04.000+0000"
        },
        {
            "id": 4,
            "comment": "comment 4",
            "time": "2020-12-21T05:33:05.000+0000"
        },
        {
            "id": 5,
            "comment": "comment 5",
            "time": "2020-12-21T05:33:06.000+0000"
        },
        {
            "id": 6,
            "comment": "comment 6,
            "time": "2020-12-21T05:33:07.000+0000"
        },
        {
            "id": 8,
            "comment": "comment 8",
            "time": "2020-12-21T05:33:08.000+0000"
        },
        {
            "id": 9,
            "comment": "comment 9",
            "time": "2020-12-21T05:33:10.000+0000"
        }
    ]
}
```