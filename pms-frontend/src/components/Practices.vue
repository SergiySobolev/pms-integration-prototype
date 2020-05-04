<template>

    <div class="container">

        <h3>Practices:</h3>
        <table class="table table-hover table-bordered">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Practice Id</th>
                    <th scope="col">Practice Name</th>
                    <th scope="col">Go Live Date</th>
                    <th scope="col">Is Coordinator Sender</th>
                    <th scope="col">Active</th>
                    <th scope="col">Details</th>
                </tr>
            </thead>
            <tbody v-if="practices">
                <tr v-for="practice in practices.practiceinfo" v-bind:key="practice.practiceid">
                    <th scope="row">{{practice.practiceid}}</th>
                    <td>{{practice.name}}</td>
                    <td>{{practice.golivedate}}</td>
                    <td>{{practice.iscoordinatorsender}}</td>
                    <td>{{practice.isactive}}</td>
                    <td>
                        <router-link v-if = "practice.isactive"
                                     :to = "{ name: 'practicedetails', params: {practiceid: practice.practiceid} }">
                            <input type="button" value="Practice details" class="btn btn-info"/>
                        </router-link>
                    </td>
                </tr>
            </tbody>
        </table>

        <BackToHome/>


    </div>
</template>


<script>
    import BackToHome from "./BackToHome";
    import { ServicesFactory } from "../services/ServicesFactory";
    const pmsService = ServicesFactory.get("pms") ;

    export default {
        name: 'Practices',
        components: {BackToHome},
        data() {
            return {
                practices: null
            };
        },
        created: function () {
            this.fetch();
        },
        methods: {
            async fetch() {
                pmsService.getPractices().then(res => {
                    this.practices = res.data;
                });
            }
        }
    }
</script>
