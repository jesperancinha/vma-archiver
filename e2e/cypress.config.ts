import {defineConfig} from "cypress";

module.exports = defineConfig({
    e2e: {
        setupNodeEvents(on, config) {
            // implement node event listeners here
        },
        env: {
            TIMEOUT_CONFIG: {
                timeout: 10000
            }
        },
        retries: {
            runMode: 4,
            openMode: 0
        },
        video: true, // enable video recording
        videosFolder: "cypress/videos", // default folder for videos
        videoCompression: 32, // lower = higher quality, 32 is default
        screenshotOnRunFailure: true, // take screenshot automatically on test failure
        screenshotsFolder: "cypress/screenshots", // default folder for screenshots
        trashAssetsBeforeRuns: true // cleanup videos/screenshots before the new run
    },
});
