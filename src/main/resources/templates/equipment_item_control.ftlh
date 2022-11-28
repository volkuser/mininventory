<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Edit equipment #${selectedEquipment.id}</h5>
    <form method="post">
        <div class="mb-3 d-flex flex-row align-items-center">
            <label class="form-label" for="inventoryNumber_id">Inventory number</label>
            <div class="p-2 d-flex flex-row">
                <div class="d-flex flex-row">
                    <input id="inventoryNumber_id" type="text" name="inventoryNumber"
                           value="${selectedEquipment.inventoryNumber}"
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
                <input id="weight_id" type="number" name="weight" value="${weight}"
                       class="form-control p-2" step="0.1">
            </label>
        </div>
        <div class="mb-3">
            <label class="form-label">Year of entry</label>
            <label>
                <input type="number" class="form-control" name="yearOfEntry" min="1900" max="2999"
                    value="${selectedEquipment.yearOfEntry!}">
            </label>
        </div>
        <div class="mb-3 d-flex flex-row align-items-center">
            <label class="form-label" for="count_id">Count</label>
            <div class="p-2 d-flex flex-row">
                <div class="d-flex flex-row">
                    <input id="count_id" type="number" value="${selectedEquipment.count}" name="count"
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
                        <#if location.equals(selectedEquipment.location)>
                            <option value="${selectedEquipment.location.id}" selected>
                                ${selectedEquipment.location.number}</option>
                        <#else>
                            <option value="${location.id}">${location.number}</option>
                        </#if>
                    </#list>
                </select>
            </label>
        </div>
        <a type="button" class="btn btn-primary" href="/equipment">Back</a>
        <button type="submit" formmethod="post" class="btn btn-primary">Update</button>
        <button type="submit" class="btn btn-primary"
                formmethod="post" formaction="/equipment/more/${selectedEquipment.id}/delete">Delete</button>
    </form>
</@c.page>