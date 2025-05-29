#SNR Characterization with Frequency-Based Filter Selection (Advantest SmarTest 8)

Overview:

This project demonstrates the automation of Signal-to-Noise Ratio (SNR) testing using Java in the Advantest SmarTest 8 test development environment. The test was designed to characterize the performance of RF filters operating at multiple frequency bands by comparing filtered vs bypass (THRU) paths.

The full solution includes relay-controlled path configuration, stimulus generation, complex waveform capture, spectral analysis, and SNR extraction — all fully automated across sites.

 Objectives:

Automate SNR testing for a DUT with RF filters at different frequency ranges

Compare performance in FILTER vs THRU (bypass) path modes

Collect waveform and frequency-domain data using SmarTest APIs

Log and visualize SNR results for QA/debug and regression purposes

⚙️ Setup Structure

Frequency Bands Tested:

50 MHz, 500 MHz, 1030 MHz, 2350 MHz

Path Configurations:

FILTER → signal passed through RF band-select filter

THRU   → signal bypassed the filter (relay-controlled)

Key Components:

@In centerFreq — frequency parameter controlling the stimulus

RlyControl.getRfIn() and getRfOut() — resolve correct signal path

RlyControl.Filter_selection() — switch relay path for FILTER or THRU

IMeasurement meas, measRly — perform stimulus + measurement and relay setup separately

Execution Flow (Summarized)

Setup:

Initialize RF stimulus at the given centerFreq

Configure measurement bandwidth, IF frequency, averaging, and sample count

Create sequential pattern blocks with stim and meas actions

Relay Configuration:

Setup relays via measRly for FILTER or THRU mode

Use the same measurement setup but apply different relay logic

Waveform Capture & Spectrum Generation:

Capture complex waveform from DUT

Compute FFT spectrum in mW and dBm

Extract the bandwidth of interest using BWParams

SNR Calculation:

Get fundamental power from max bin

Get noise power by summing spectrum bins

Apply: SNR_dB = 10 * log10(Fund / Noise)

Logging and Plotting:

Plot waveform and spectra for visual inspection

Log SNR value 


📈 Results

Each frequency band was tested in both FILTER and THRU mode.
SNR results were visualized using internal plot tools and logged per site.
This allows engineers to:

Verify filter performance

Compare signal degradation

Spot anomalies like poor isolation or unexpected noise

💡 What This Demonstrates

✅ Proficiency in SmarTest 8 API (Java)✅ Automation of complex RF test flows✅ Real-world filter testing with programmable relays✅ Integration of FFT-based signal processing✅ Multi-site execution with logging and waveform visualization

