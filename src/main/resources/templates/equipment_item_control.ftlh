<#import "parts/common.ftl" as c>

<@c.page>
    <h5>Edit equipment #${selectedEquipment.id}</h5>
    <form method="post">
        <div class="mb-3">
            <label class="form-label">Inventory number</label>
            <label>
                <input type="text" class="form-control" name="inventoryNumber"
                    value="${selectedEquipment.inventoryNumber}">
            </label>
        </div>
        <div class="mb-3">
            <label class="form-label">Weight</label>
            <label>
                <input type="number" class="form-control" min="0.01" step="0.01" name="weight"
                       value="${weight!'0.01'}">
            </label>
        </div>
        <div class="mb-3">
            <label class="form-label">Year of entry</label>
            <label>
                <input type="number" class="form-control" name="yearOfEntry" min="1900" max="2999"
                    value="${selectedEquipment.yearOfEntry!'1900'}">
            </label>
        </div>
        <div class="mb-3">
            <label class="form-label">Count</label>
            <label>
                <input type="number" class="form-control" min="1" name="count"
                    value="${selectedEquipment.count!'1'}">
            </label>
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