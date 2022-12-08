<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.2.1/css/all.min.css">
</head>
<style>
    input[type=number]::-webkit-inner-spin-button,
    input[type=number]::-webkit-outer-spin-button {
        -webkit-appearance: none;
        -moz-appearance: none;
        appearance: none;
        margin: 0;
    }
</style>
<body>


<div class="container text-center mt-5 pt-5">
    <h1>Manager</h1>
</div>

<div class="container">
    <table>
        <tr>
            <td>
                <button type="button" class="btn btn-dark mb-5 mt-5" data-bs-toggle="modal" data-bs-target="#create">Add new
                    employee
                </button>
            </td>
            <td>
                <form action="employee?action=search" method="post" class="mb-2 mt-2">
                    <input class="ms-5 me-0" type="text" name="searchWord" id="" placeholder="Search">
                    <button class="ms-0" style="border-radius: 3px"><i class="fa-brands fa-searchengin me-0"></i></button>
                </form>
            </td>
        </tr>
    </table>
</div>

<table class=" container table table-bordered table-sm">
    <tr class="text-center">
        <th>#</th>
        <th>Name</th>
        <th>Email</th>
        <th>Address</th>
        <th>Phone number</th>
        <th>Salary</th>
        <th>Department</th>
        <th>Action</th>
    </tr>

    <c:forEach items="${employees}" var="e">
        <tr>
            <td class="text-center">${e.getId()}</td>
            <td>${e.getName()}</td>
            <td>${e.getEmail()}</td>
            <td class="text-center">${e.getAddress()}</td>
            <td class="text-center">${e.getPhoneNumber()}</td>
            <td class="text-center">${e.getSalary()}</td>
            <td class="text-center">${e.getDepartment().getName()}</td>
            <td class="text-center">
                <button data-bs-toggle="modal" data-bs-target="#edit${e.getId()}"
                        style="border: none; background-color: white"><i class="fa-solid fa-pen-to-square"></i>
                </button>
                <button onclick="checkDelete('${e.getName()}', 'employee?action=delete&id=${e.getId()}')"
                        style="border: none; background-color: white"><i class="fa-solid fa-trash-can"></i>
                </button>
            </td>

                <%--Modal edit start--%>
            <div class="modal fade" id="edit${e.getId()}" tabindex="-1" aria-labelledby="exampleModalLabel"
                 aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title">Edit Form</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal"
                                    aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="employee?action=edit" method="post">
                                <input type="text" name="id" value="${e.getId()}" hidden="hidden">
                                <div class="mb-3">
                                    <label for="name" class="form-label">Name</label>
                                    <input type="text" class="form-control" id="name${e.getId()}" name="name"
                                           value="${e.getName()}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="email" class="form-label">Email</label>
                                    <input type="email" class="form-control" id="email${e.getId()}" name="email"
                                           value="${e.getEmail()}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="address" class="form-label">Address</label>
                                    <input type="text" class="form-control" id="address${e.getId()}"
                                           name="address"
                                           value="${e.getAddress()}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="phoneNumber" class="form-label">Phone number</label>
                                    <input type="number" class="form-control" id="phoneNumber${e.getId()}"
                                           name="phoneNumber"
                                           value="${e.getPhoneNumber()}" required>
                                </div>
                                <div class="mb-3">
                                    <label for="salary" class="form-label">Salary</label>
                                    <input type="number" class="form-control" id="address${e.getId()}"
                                           name="salary"
                                           value="${e.getSalary()}" required>
                                </div>
                                <select class="form-select" aria-label="Default select example" name="departmentId">
                                    <c:forEach items="${departments}" var="d">
                                        <c:if test="${d.getId() != e.getDepartment().getId()}">
                                            <option value="${d.getId()}"><c:out
                                                    value="${d.getName()}"/></option>
                                        </c:if>
                                        <c:if test="${d.getId() == e.getDepartment().getId()}">
                                            <option selected value="${d.getId()}"><c:out
                                                    value="${d.getName()}"/></option>
                                        </c:if>
                                    </c:forEach>
                                </select>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">
                                        Close
                                    </button>
                                    <button type="submit" class="btn btn-dark">Save changes</button>
                                </div>
                            </form>
                        </div>

                    </div>
                </div>
            </div>
                <%--Modal edit end--%>
        </tr>
    </c:forEach>
</table>


<!-- Modal add new employee start-->
<div class="modal fade" id="create" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Add new employee</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="employee?action=create" method="post">
                    <div class="mb-3">
                        <label for="name" class="form-label">Name</label>
                        <input type="text" class="form-control" id="name" name="name" placeholder="Enter name" required>
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" required>
                    </div>
                    <div class="mb-3">
                        <label for="address" class="form-label">Address</label>
                        <input type="text" class="form-control" id="address" placeholder="Enter address" name="address" required>
                    </div>
                    <div class="mb-3">
                        <label for="phoneNumber" class="form-label">Phone number</label>
                        <input type="number" class="form-control" id="phoneNumber" placeholder="Enter phone number" name="phoneNumber" required>
                    </div>
                    <div class="mb-3">
                        <label for="salary" class="form-label">Salary</label>
                        <input type="number" class="form-control" id="salary" placeholder="Enter salary" name="salary" required>
                    </div>
                    <select class="form-select" aria-label="Default select example" name="departmentId" required>
                        <option value="">Select department</option>
                        <c:forEach items="${departments}" var="d">
                            <option value="${d.getId()}">${d.getName()}</option>
                        </c:forEach>
                    </select>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-dark">Save changes</button>
                    </div>
                </form>
            </div>

        </div>
    </div>
</div>
<!-- Modal add new employee end-->

</body>
</html>
<script>
    function checkDelete(name, path) {
        if (confirm('Are you really want to delete ' + name)) {
            window.location.href = path;
        }
    }
</script>
