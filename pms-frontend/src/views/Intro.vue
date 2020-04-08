<template>

    <div id="nav">

        <h2> Demonstration of integration with Practice Management Systems API </h2>

        <pre class="tab">
            <router-link to="/home"><input type="button" value="Start Demo" class="btn btn-primary"/></router-link>

            <router-link to="/architecture"><input type="button" value="Demo's architecture" class="btn btn-info"/></router-link>

            <router-link to="/pms"><input type="button" value="About integrated systems" class="btn btn-info"/></router-link>
        </pre>

    </div>

</template>


<script>
    export default {
        name: 'Intro',
    }
</script>

<style>
    h3 {
        margin-bottom: 5%;
    }
</style>