package com.dzx.sound.midi;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//



public interface ReferenceCountingDevice {
    Receiver getReceiverReferenceCounting() throws MidiUnavailableException;

    Transmitter getTransmitterReferenceCounting() throws MidiUnavailableException;
}

