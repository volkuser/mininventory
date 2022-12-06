<#import "parts/common.ftl" as c>

<@c.page>
    <h3>Equipment control</h3>
    <div class="d-flex flex-row mb-3">
        <div class="p-2">
            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Inventory number</th>
                        <th scope="col">Weight</th>
                        <th scope="col">Year of entry</th>
                        <th scope="col">Count</th>
                        <th scope="col">Location (number)</th>
                    </tr>
                </thead>
                    <tbody>
                    <#list equipments as equipment>
                        <tr>
                            <td>${equipment.id}</td>
                            <td>${equipment.inventoryNumber}</td>
                            <td>${equipment.weight}</td>
                            <td>${equipment.yearOfEntry}</td>
                            <td>${equipment.count}</td>
                            <td>${equipment.location.number}</td>
                            <td><a href="equipment/more/${equipment.id}">More</a></td>
                        </tr>
                    <#else>
                        <td>no equipments</td>
                    </#list>
                </tbody>
            </table>
        </div>
        <div class="d-flex flex-column mb-3">
            <div class="p-2">
                <form method="get">
                    <h5>Search</h5>
                    <div class="mb-3">
                        <label>
                            <input type="text" class="form-control" name="query"/>
                        </label>
                    </div>
                    <div class="p-1">
                        <button type="submit" class="btn btn-primary" formaction="/equipment/exact"
                                formmethod="get">Exact search</button>
                    </div>
                    <div class="p-1">
                        <button type="submit" class="btn btn-primary" formaction="/equipment/imprecise"
                                formmethod="get">Imprecise search</button>
                    </div>
                </form>
            </div>
            <div class="p-2">
                <h5>Add new equipment</h5>
                <form method="post">
                    <div class="mb-3 d-flex flex-row align-items-center">
                        <label class="form-label" for="inventoryNumber_id">Inventory number</label>
                        <div class="p-2 d-flex flex-row">
                            <div class="d-flex flex-row">
                                <input id="inventoryNumber_id" type="text" name="inventoryNumber"
                                       class="form-control ${(inventoryNumberError??)?string('is-invalid', '')} p-2">
                                <#if inventoryNumberError??>
                                    <div class="invalid-feedback p-2">
                                        ${inventoryNumberError}
                                    </div>
                                </#if>
                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label" for="weight_id">Weight</label>
                        <label>
                            <input id="weight_id" type="number" name="weight" value="0.1"
                                   class="form-control p-2" step="0.1">
                        </label>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Year of entry</label>
                        <label>
                            <input type="number" class="form-control" name="yearOfEntry" min="1900" max="2999"
                                   value="1900">
                        </label>
                    </div>
                    <div class="mb-3 d-flex flex-row align-items-center">
                        <label class="form-label" for="count_id">Count</label>
                        <div class="p-2 d-flex flex-row">
                            <div class="d-flex flex-row">
                                <input id="count_id" type="number" value="1" name="count"
                                       class="form-control ${(countError??)?string('is-invalid', '')} p-2">
                                <#if countError??>
                                    <div class="invalid-feedback p-2">
                                        ${countError}
                                    </div>
                                </#if>
                            </div>
                        </div>
                    </div>
                    <div class="mb-3">
                        <label class="form-label">Location</label>
                        <label>
                            <select name="location" class="form-control">
                                <#list locations as location>
                                    <option value="${location.id}">${location.number}</option>
                                </#list>
                            </select>
                        </label>
                    </div>
                    <div><input type="hidden" name="_csrf" value="${_csrf.token}"></div>
                    <button type="submit" class="btn btn-primary">Add</button>
                </form>
            </div>
        </div>
    </div>
</@c.page>