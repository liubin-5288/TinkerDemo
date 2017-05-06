/*
 * Tencent is pleased to support the open source community by making Tinker available.
 *
 * Copyright (C) 2016 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the BSD 3-Clause License (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/BSD-3-Clause
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.tinkertest.tinker.reporter;

import android.content.Context;
import android.content.Intent;

import com.example.tinkertest.tinker.util.UpgradePatchRetry;
import com.tencent.tinker.lib.reporter.DefaultPatchReporter;
import com.tencent.tinker.loader.shareutil.SharePatchInfo;

import java.io.File;


/**
 * 你可以使用默认的 DefaultPatchReporter
 */
public class TinkerPatchReporter extends DefaultPatchReporter {
    public TinkerPatchReporter(Context context) {
        super(context);
    }

    @Override
    public void onPatchServiceStart(Intent intent) {
        super.onPatchServiceStart(intent);
        CustomTinkerReport.onApplyPatchServiceStart();
        UpgradePatchRetry.getInstance(context).onPatchServiceStart(intent);
    }

    @Override
    public void onPatchDexOptFail(File patchFile, File dexFile, String optDirectory, String dexName, Throwable t) {
        super.onPatchDexOptFail(patchFile, dexFile, optDirectory, dexName, t);
        CustomTinkerReport.onApplyDexOptFail(t);
    }

    @Override
    public void onPatchException(File patchFile, Throwable e) {
        super.onPatchException(patchFile, e);
        CustomTinkerReport.onApplyCrash(e);
    }

    @Override
    public void onPatchInfoCorrupted(File patchFile, String oldVersion, String newVersion) {
        super.onPatchInfoCorrupted(patchFile, oldVersion, newVersion);
        CustomTinkerReport.onApplyInfoCorrupted();
    }

    @Override
    public void onPatchPackageCheckFail(File patchFile, int errorCode) {
        super.onPatchPackageCheckFail(patchFile, errorCode);
        CustomTinkerReport.onApplyPackageCheckFail(errorCode);
    }

    @Override
    public void onPatchResult(File patchFile, boolean success, long cost) {
        super.onPatchResult(patchFile, success, cost);
        CustomTinkerReport.onApplied(cost, success);
        UpgradePatchRetry.getInstance(context).onPatchServiceResult();
    }

    @Override
    public void onPatchTypeExtractFail(File patchFile, File extractTo, String filename, int fileType) {
        super.onPatchTypeExtractFail(patchFile, extractTo, filename, fileType);
        CustomTinkerReport.onApplyExtractFail(fileType);
    }

    @Override
    public void onPatchVersionCheckFail(File patchFile, SharePatchInfo oldPatchInfo, String patchFileVersion) {
        super.onPatchVersionCheckFail(patchFile, oldPatchInfo, patchFileVersion);
        CustomTinkerReport.onApplyVersionCheckFail();
    }
}
