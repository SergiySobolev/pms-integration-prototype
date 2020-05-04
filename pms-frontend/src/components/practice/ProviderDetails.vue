<template>
    <div id="providerdetails" class="container">

        <div id="providerattributes" class="row border border-primary">

            <div class="col">
                <img src="https://www.meme-arsenal.com/memes/e71c81648ee8ac95f46b5f6f0d33092d.jpg" alt="Provider"/>
            </div>

            <div class="col">

                <div class="form-group">
                    <label for="firstname">First Name</label>
                    <div>
                        <input type="text" v-model="provider.firstname" id="firstname" class="form-control" readonly/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="lastname">Last Name</label>
                    <div>
                        <input type="text" v-model="provider.lastname" id="lastname" class="form-control" readonly/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="specialty">Specialty</label>
                    <div>
                        <input type="text" v-model="provider.specialty" id="specialty" class="form-control" readonly/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="ansinamecode">Ansiname Code</label>
                    <div>
                        <input type="text" v-model="provider.ansinamecode" id="ansinamecode" class="form-control" readonly/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="homedepartment">Home Department</label>
                    <div>
                        <input type="text" v-model="provider.homedepartment" id="homedepartment" class="form-control" readonly/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="providertype">Provider Type</label>
                    <div>
                        <input type="text" v-model="provider.providertype" id="providertype" class="form-control" readonly/>
                    </div>
                </div>

            </div>
        </div>

        <ProviderAppointmentTypes
                :practiceid="practiceid"
                :providerid="providerid"
                :departmentid="departmentid"
        />

    </div>
</template>

<script>
    import {ServicesFactory} from "../../services/ServicesFactory"
    import ProviderAppointmentTypes from "./ProviderAppointmentTypes";

    const pmsService = ServicesFactory.get("pms");

    export default {
        name: "ProviderDetails",
        components: {ProviderAppointmentTypes},
        props: {
            providerid: String,
            practiceid: String,
        },
        data() {
            return {
                departmentid: 1,
                provider: null
            };
        },
        created: function () {
            this.fetch();
        },
        methods: {
            async fetch() {
                pmsService.getProvider(this.practiceid, this.providerid).then(res => {
                    this.provider = res.data;
                });
            }
        }
    }
</script>
